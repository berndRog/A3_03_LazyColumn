package de.rogallab.mobile.ui.features.people

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import de.rogallab.mobile.data.PeopleRepository
import de.rogallab.mobile.data.local.DataStore
import de.rogallab.mobile.domain.ResultData
import de.rogallab.mobile.domain.utilities.logDebug
import de.rogallab.mobile.domain.utilities.logError
import de.rogallab.mobile.domain.utilities.logVerbose
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PeopleViewModel(
   application: Application
): AndroidViewModel(application) {

   // we must fix this by using a dependency injection framework
   val context = getApplication<Application>().applicationContext
   val dataStore = DataStore(context)
   val repository = PeopleRepository(dataStore)

   // read dataStore when ViewModel is created
   init {
      logDebug(TAG, "init readDataStore()")
      repository.readDataStore()
   }
   // write dataStore when ViewModel is cleared
   override fun onCleared() {
      logVerbose(TAG, "onCleared()")
      repository.writeDataStore()
      super.onCleared()
   }

   // PeopleListScreen <=> PeopleViewModel
   private var _peopleUiStateFlow: MutableStateFlow<PeopleUiState> = MutableStateFlow(PeopleUiState())
   val peopleUiStateFlow: StateFlow<PeopleUiState>
      get() = _peopleUiStateFlow.asStateFlow()

   // read all people from repository
   fun fetchPeople() {
      logDebug(TAG, "fetchPeople")
      when (val resultData = repository.getAll()) {
         is ResultData.Success -> {
            _peopleUiStateFlow.update { it: PeopleUiState ->
               it.copy(people = resultData.data.toList())
            }
            logDebug(TAG, "people.count: ${peopleUiStateFlow.value.people.size}")
         }
         is ResultData.Error -> {
            val message = "Failed to fetch people ${resultData.throwable.localizedMessage}"
            logError(TAG, message)
         }
         else -> Unit
      }
   }


   // PERSON SCREEN <=> PeopleViewModel
   private val _personUiStateFlow: MutableStateFlow<PersonUiState> = MutableStateFlow(PersonUiState())
   val personUiStateFlow: StateFlow<PersonUiState> = _personUiStateFlow.asStateFlow()

   fun onFirstNameChanged(firstName: String) {
      _personUiStateFlow.update { it:PersonUiState ->
         it.copy(person = it.person.copy(firstName = firstName))
      }
   }
   fun onLastNameChanged(lastName: String) {
      _personUiStateFlow.update { it:PersonUiState ->
         it.copy(person = it.person.copy(lastName = lastName))
      }
   }

   fun removePerson(personId: String) {
      logDebug(TAG, "removePerson: $personId")
      when(val resultData = repository.remove(personId)) {
         is ResultData.Success -> fetchPeople()
         is ResultData.Error -> {
            val message = "Failed to remove a person ${resultData.throwable.localizedMessage}"
            logError(TAG, message)
         }
         else -> Unit
      }
   }

   companion object {
      private const val TAG = "[PeopleViewModel]"
   }
}