package de.rogallab.mobile.ui.features.people

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import de.rogallab.mobile.data.PeopleRepository
import de.rogallab.mobile.data.database.DataStore
import de.rogallab.mobile.domain.ResultData
import de.rogallab.mobile.domain.entities.Person
import de.rogallab.mobile.domain.utilities.logDebug
import de.rogallab.mobile.domain.utilities.logError
import de.rogallab.mobile.domain.utilities.logVerbose
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

class PeopleViewModel(application: Application): AndroidViewModel(application) {

   val context = getApplication<Application>().applicationContext
   val dataStore = DataStore(context)
   val repository = PeopleRepository(dataStore)

   // read dataStore when ViewModel is created
   init {
      logDebug(tag, "init readDataStore()")
      repository.readDataStore()
   }
   // write dataStore when ViewModel is cleared
   override fun onCleared() {
      logVerbose(tag, "onCleared()")
      repository.writeDataStore()
      super.onCleared()
   }

   // Data Binding PersonScreen <=> PeopleViewModel
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

   // Data Binding PeopleListScreen <=> PeopleViewModel
   private val _peopleUiStateFlow: MutableStateFlow<PeopleUiState> = MutableStateFlow(PeopleUiState())
   val peopleUiStateFlow: StateFlow<PeopleUiState> = _peopleUiStateFlow.asStateFlow()

   // read all people from repository
   fun readPeople() {
      logDebug(tag, "readPeople")
      when (val resultData = repository.getAll()) {
         is ResultData.Success -> {
            _peopleUiStateFlow.update { it: PeopleUiState ->
               it.copy(people = resultData.data)
            }
            logDebug(tag, "people.count: ${resultData.data?.size}")
         }
         is ResultData.Error -> {
            val message = "Failed to read people ${resultData.throwable.localizedMessage}"
            logError(tag, message)
         }
         else -> Unit
      }
   }

   fun readPerson(personId: UUID) {
      logDebug(tag, "readPerson: $personId")
      when (val resultData = repository.getById(personId)) {
         is ResultData.Success -> {
            _personUiStateFlow.update { it: PersonUiState ->
               it.copy(person = resultData.data ?: Person())  // new UiState
            }
         }
         is ResultData.Error -> {
            val message = "Failed to read person ${resultData.throwable.localizedMessage}"
            logError(tag, message)
         }
         else -> Unit
      }
   }

   fun createPerson() {
      logDebug(tag, "createPerson")
      when (val resultData = repository.create(_personUiStateFlow.value.person)) {
         is ResultData.Success -> readPeople()
         is ResultData.Error -> {
            val message = "Failed to create a person ${resultData.throwable.localizedMessage}"
            logError(tag, message)
         }
         else -> Unit
      }
   }

   fun updatePerson() {
      logDebug(tag, "updatePerson")
      val resultData = repository.update(_personUiStateFlow.value.person)
      if (resultData is ResultData.Error) {
         val message = "Failed to update a person ${resultData.throwable.localizedMessage}"
         logError(tag,message)
      }
   }

   fun removePerson(personId: UUID) {
      logDebug(tag, "removePerson: $personId")
      when(val resultData = repository.remove(personId)) {
         is ResultData.Success -> readPeople()
         is ResultData.Error -> {
            val message = "Failed to delete a person ${resultData.throwable.localizedMessage}"
            logError(tag, message)
         }
         else -> Unit
      }
   }


   companion object {
      private const val tag = "[PeopleViewModel]"
   }
}