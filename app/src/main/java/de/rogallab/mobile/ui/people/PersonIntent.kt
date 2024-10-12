package de.rogallab.mobile.ui.people

sealed class PersonIntent {
   data class  FirstNameChange(val firstName: String) : PersonIntent()
   data class  LastNameChange(val lastName: String) : PersonIntent()
   data object Create : PersonIntent()
   data class  Remove(val id: String) : PersonIntent()
}