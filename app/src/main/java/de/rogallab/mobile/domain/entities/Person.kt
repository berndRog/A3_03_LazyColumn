package de.rogallab.mobile.domain.entities

import de.rogallab.mobile.domain.utilities.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Person (
   val firstName: String = "",
   val lastName: String = "",
   @Serializable(with = UUIDSerializer::class)
   val id: UUID = UUID.randomUUID()
)