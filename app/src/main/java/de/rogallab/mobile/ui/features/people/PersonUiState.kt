package de.rogallab.mobile.ui.features.people

import de.rogallab.mobile.domain.entities.Person

data class PersonUiState(
   val person: Person = Person(),
   val throwable: Throwable? = null
)