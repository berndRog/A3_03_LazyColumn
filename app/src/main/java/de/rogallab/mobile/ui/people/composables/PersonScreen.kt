package de.rogallab.mobile.ui.people.composables

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.rogallab.mobile.ui.people.PeopleViewModel

@Composable
fun PersonScreen(
   viewModel: PeopleViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
   val personUiState by viewModel.personUiStateFlow.collectAsStateWithLifecycle()


   // No errors occurred
   if (personUiState.throwable == null) {
      // Show the input fields
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
   } else {
      // Show a toast with the error message
      personUiState.throwable?.let { it: Throwable ->
         val message = "!!! ---     " + it.message + "    ---!!!"
         Toast
            .makeText(LocalContext.current, message, Toast.LENGTH_LONG)
            .show()
      }
   }
}