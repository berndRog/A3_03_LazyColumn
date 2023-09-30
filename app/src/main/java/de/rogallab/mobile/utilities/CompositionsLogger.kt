package de.rogallab.mobile.utilities

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import de.rogallab.mobile.MainActivity.Companion.isDebug

// https://www.jetpackcompose.app/articles/how-can-I-debug-recompositions-in-jetpack-compose

@Composable
fun LogComp(tag: String, msg: String) {
   if (isDebug) {
      val ref = remember { Ref(1) }
      SideEffect { ref.value++ }
      Log.d(tag, "Compositions ${ref.value}: $msg")
   }
}

class Ref(var value: Int)

fun LogDebug(tag: String, message: String) {
   if (isDebug) {
      val msg = formatMessage(message)
      Log.d(tag, msg)
   }
}

fun LogInfo(tag: String, message: String) {
   val msg = formatMessage(message)
   Log.i(tag, msg)
}

private fun formatMessage(message: String) =
   String.format("%-100s %s", message, Thread.currentThread().toString())
