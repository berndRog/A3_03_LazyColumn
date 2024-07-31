package de.rogallab.mobile.domain.utilities

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.Serializer
import java.util.UUID

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = UUID::class)
object UUIDSerializer{
   override fun serialize(encoder: Encoder, value: UUID) =
      encoder.encodeString(value.toString())

   override fun deserialize(decoder: Decoder): UUID {

      return try {
         UUID.fromString(decoder.decodeString())
      } catch (e: IllegalArgumentException) {
         logError("[UUIDSerializer]", "Invalid UUID string: ${e.message}")// Handle invalid UUID string, e.g., log the error or return a default UUID
         UUIDEmpty
      }
   }
}