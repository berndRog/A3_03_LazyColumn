package de.rogallab.mobile.ui.people

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import de.rogallab.mobile.data.IDataStore
import de.rogallab.mobile.data.local.DataStore
import de.rogallab.mobile.data.repositories.PeopleRepository
import de.rogallab.mobile.domain.IPeopleRepository
import de.rogallab.mobile.domain.ResultData
import de.rogallab.mobile.domain.entities.Person
import de.rogallab.mobile.domain.utilities.as8
import de.rogallab.mobile.domain.utilities.logDebug
import de.rogallab.mobile.domain.utilities.logError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PeopleViewModel(
   application: Application
): AndroidViewModel(application) {

   // we must fix this by using a dependency injection framework
   private val _context: Context = application.applicationContext
   private val _dataStore: IDataStore = DataStore(_context)
   private val _repository: IPeopleRepository = PeopleRepository(_dataStore)

   // PEOPLE LIST SCREEN <=> PeopleViewModel
   private var _peopleUiStateFlow: MutableStateFlow<PeopleUiState> = MutableStateFlow(PeopleUiState())
   val peopleUiStateFlow: StateFlow<PeopleUiState>
      get() = _peopleUiStateFlow.asStateFlow()

   // transform intent into an action
   fun onProcessIntent(intent: PeopleIntent) {
      when (intent) {
         is PeopleIntent.FetchPeople -> fetch()
      }
   }

   // read all people from repository
   private fun fetch() {
      logDebug(TAG, "fetchPeople")
      when (val resultData = _repository.getAll()) {
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
      }
   }

   // PERSON SCREEN <=> PeopleViewModel
   private val _personUiStateFlow: MutableStateFlow<PersonUiState> = MutableStateFlow(PersonUiState())
   val personUiStateFlow: StateFlow<PersonUiState> = _personUiStateFlow.asStateFlow()

   // transform intent into an action
   fun onProcessIntent(intent: PersonIntent) {
      when (intent) {
         is PersonIntent.FirstNameChange -> onFirstNameChange(intent.firstName)
         is PersonIntent.LastNameChange -> onLastNameChange(intent.lastName)
         is PersonIntent.Create -> create()
         is PersonIntent.Remove -> remove(intent.person)
      }
   }

   private fun onFirstNameChange(firstName: String) {
      _personUiStateFlow.update { it: PersonUiState ->
         it.copy(person = it.person.copy(firstName = firstName))
      }
   }
   private fun onLastNameChange(lastName: String) {
      _personUiStateFlow.update { it: PersonUiState ->
         it.copy(person = it.person.copy(lastName = lastName))
      }
   }

   private fun create() {
      logDebug(TAG, "createPerson")
      when (val resultData = _repository.create(_personUiStateFlow.value.person)) {
         is ResultData.Success -> fetch()
         is ResultData.Error -> {
            val message = "Failed to create a person ${resultData.throwable.localizedMessage}"
            logError(TAG, message)
         }
      }
   }
   private fun remove(person: Person) {
      logDebug(TAG, "removePerson() ${person.id.as8()}")
      when(val resultData = _repository.remove(person)) {
         is ResultData.Success -> fetch()
         is ResultData.Error -> {
            val message = "Failed to remove a person ${resultData.throwable.localizedMessage}"
            logError(TAG, message)
         }
      }
   }

   companion object {
      private const val TAG = "<-PeopleViewModel"
   }
}