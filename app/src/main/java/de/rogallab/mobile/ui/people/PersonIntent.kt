package de.rogallab.mobile.ui.people

sealed class PersonIntent {
   data class  FirstNameChanged(val firstName: String) : PersonIntent()
   data class  LastNameChanged(val lastName: String) : PersonIntent()
   data object CreatePerson : PersonIntent()
   data class  RemovePerson(val id: String) : PersonIntent()
}