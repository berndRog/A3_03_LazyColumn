package de.rogallab.mobile.ui.people

import de.rogallab.mobile.domain.entities.Person

sealed class PersonIntent {
   data class  FirstNameChange(val firstName: String) : PersonIntent()
   data class  LastNameChange(val lastName: String) : PersonIntent()
   data object Create : PersonIntent()
   data class  Remove(val person: Person) : PersonIntent()
}