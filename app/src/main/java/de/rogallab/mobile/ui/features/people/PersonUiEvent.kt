package de.rogallab.mobile.ui.features.people

sealed interface PersonUiEvent {
   data class OnFirstNameChanged(val firstName: String) : PersonUiEvent
   data class OnLastNameChanged(val lastName: String) : PersonUiEvent
   data class OnError(val throwable: Throwable) : PersonUiEvent
}