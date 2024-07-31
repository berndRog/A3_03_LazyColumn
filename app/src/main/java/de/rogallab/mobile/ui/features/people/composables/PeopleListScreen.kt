package de.rogallab.mobile.ui.features.people.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.rogallab.mobile.domain.entities.Person
import de.rogallab.mobile.domain.utilities.logVerbose
import de.rogallab.mobile.ui.features.people.PeopleViewModel
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeopleListScreen(
   viewModel: PeopleViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
   val tag = "[PeopleListScreen]"

   val peopleUiState by viewModel.peopleUiStateFlow.collectAsStateWithLifecycle()

   LaunchedEffect(Unit, peopleUiState) {
      logVerbose(tag, "peopleUiState: ${peopleUiState.people.size}")
      viewModel.readPeople()
   }
   val sortedPeople = peopleUiState.people.sortedBy { it.firstName }
   Column(modifier = Modifier
      .fillMaxSize()
   ) {
      TopAppBar(
         title = {
            Text("People")
         }
      )
      val sortedPeople = peopleUiState.people.sortedBy { it.firstName }
      LazyColumn(
         modifier = Modifier
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp),
         state = rememberLazyListState()
      ) {
         items(
            items = sortedPeople,
            key = { it: Person -> it.id }
         ) { person ->
            PersonListItem(
               id = person.id,
               firstName = person.firstName,
               lastName = person.lastName,
               onClicked = {
                  //viewModel.
               },
               onDeleted = viewModel::removePerson
            )
         }
      }
   }
}
@Composable
fun PersonListItem(
   id: UUID,
   firstName: String,
   lastName: String,
   onClicked: (UUID) -> Unit,
   onDeleted: (UUID) -> Unit
) {
   Column {

      HorizontalDivider()

      Row(
         verticalAlignment = Alignment.CenterVertically,
         modifier = Modifier
            .padding(vertical = 4.dp)
            .clickable {
               onClicked(id)
            }
      ) {
         Column(
            modifier = Modifier
               .weight(0.9f)
               .padding(start = 4.dp)
         ) {
            Text(
               text = "$firstName $lastName",
               style = MaterialTheme.typography.bodyLarge
            )
         }

         IconButton(
            onClick = {
               onDeleted(id)
            }, // Event ↑
            modifier = Modifier
               .weight(0.1f)
               .padding(end = 8.dp)
         ) {
            Icon(
               imageVector = Icons.Filled.Close,
               contentDescription = "Delete item"
            )
         }
      }
   }        // Event ↑  lumn(modifier = Modifier.weight(0.7f) ) { …
}