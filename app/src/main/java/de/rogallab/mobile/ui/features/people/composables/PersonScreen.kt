package de.rogallab.mobile.ui.features.people.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import de.rogallab.mobile.ui.features.people.PeopleViewModel

@Composable
fun PersonScreen(
   viewModel: PeopleViewModel = viewModel()
) {

   val personUiState by viewModel.personUiStateFlow.collectAsStateWithLifecycle()

   Column(modifier = Modifier.padding(all = 8.dp).fillMaxSize()) {
      InputName(
         name = personUiState.person.firstName,
         onNameChange = viewModel::onFirstNameChanged,
         label = "Vorname"
      )
      InputName(
         name = personUiState.person.lastName,
         onNameChange = viewModel::onLastNameChanged,
         label = "Nachname"
      )
   }
}


