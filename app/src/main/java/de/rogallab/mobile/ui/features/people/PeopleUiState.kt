package de.rogallab.mobile.ui.features.people

import de.rogallab.mobile.domain.entities.Person

data class PeopleUiState(
   val isLoading: Boolean = false,
   val people: List<Person> = emptyList(),
   val error: Throwable? = null
)