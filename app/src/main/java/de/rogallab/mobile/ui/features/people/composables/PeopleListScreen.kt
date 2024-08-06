package de.rogallab.mobile.ui.features.people.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.rogallab.mobile.domain.entities.Person
import de.rogallab.mobile.domain.utilities.logInfo
import de.rogallab.mobile.domain.utilities.logVerbose
import de.rogallab.mobile.ui.features.people.PeopleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeopleListScreen(
   viewModel: PeopleViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
   val tag = "[PeopleListScreen]"

   // observe the peopleUiStateFlow in the ViewModel
   // notify the UI when the state changes
   val peopleUiState by viewModel.peopleUiStateFlow.collectAsStateWithLifecycle()

   // read all people from repository, when the screen is created
   LaunchedEffect(Unit) {
      logVerbose(tag, "readPeople()")
      viewModel.fetchPeople()
   }

   Column(modifier = Modifier.fillMaxSize()) {
      TopAppBar(  title = { Text("People") } )

      LazyColumn(
         modifier = Modifier.padding(top = 16.dp)
                            .padding(horizontal = 16.dp),
         state = rememberLazyListState()
      ) {
         items(
            items = peopleUiState.people,
            key = { it: Person -> it.id }
         ) { person ->
            PersonListItem(
               id = person.id,
               firstName = person.firstName,
               lastName = person.lastName,
               onClicked = { id: String ->
                  logInfo(tag, "Person clicked: $id")
               },
               onDeleted = { id: String ->
                  logInfo(tag, "Person deleted: $id")
                  viewModel.removePerson(id)
               }
            )
         }
      }
   }
}
