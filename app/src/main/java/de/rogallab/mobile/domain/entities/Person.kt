package de.rogallab.mobile.domain.entities

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
@Immutable
data class Person (
   val firstName: String = "",
   val lastName: String = "",
   val id: String = UUID.randomUUID().toString()
)