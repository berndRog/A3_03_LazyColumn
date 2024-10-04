package de.rogallab.mobile.ui.people

import de.rogallab.mobile.domain.entities.Person

data class PersonUiState(
   val person: Person = Person(),
   val throwable: Throwable? = null
)