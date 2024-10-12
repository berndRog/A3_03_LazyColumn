package de.rogallab.mobile.ui.people

sealed class PersonIntent {
   data class  FirstNameChange(val firstName: String) : PersonIntent()
   data class  LastNameChange(val lastName: String) : PersonIntent()
   data object CreatePerson : PersonIntent()
   data class  RemovePerson(val id: String) : PersonIntent()
}