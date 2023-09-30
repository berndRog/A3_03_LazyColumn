package de.rogallab.mobile

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import de.rogallab.mobile.utilities.LogInfo

open class BaseActivity(
   private val _tag: String
) : ComponentActivity() {


   // Activity is first created
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      if (savedInstanceState == null)
         LogInfo(_tag, "onCreate() Bundle == null")
      else
         LogInfo(_tag, "onCreate() Bundle != null")
   }

   // Activity is restarted
   override fun onRestart() {
      super.onRestart()
      LogInfo(_tag, "onRestart()")
   }

   // Activity is visible
   override fun onStart() {
      super.onStart()
      LogInfo(_tag, "onStart()")
   }

   // Activity interacts with the user
   override fun onResume() {
      super.onResume()
      LogInfo(_tag, "onResume()")
   }

   // User is leaving activity
   override fun onPause() {
      LogInfo(_tag, "onPause()")
      super.onPause()
   }

   // Activity is no longer visible
   override fun onStop() {
      LogInfo(_tag, "onStop()")
      super.onStop()
   }

   // Called before the activity is destroyed.
   override fun onDestroy() {
      LogInfo(_tag, "onDestroy()")
      super.onDestroy()
   }

   // Save instance state: invoked when the activity may be temporarily destroyed,
   override fun onSaveInstanceState(savedStateBundle: Bundle) {
      super.onSaveInstanceState(savedStateBundle)
      LogInfo(_tag, "onSaveInstanceState()")
   }

   override fun onRestoreInstanceState(savedInstanceState: Bundle) {
      super.onRestoreInstanceState(savedInstanceState)
      LogInfo(_tag, "onRestoreInstanceState()")
   }

   override fun onConfigurationChanged(newConfig: Configuration) {
      super.onConfigurationChanged(newConfig)

      // Checks the orientation of the screen
      if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
         LogInfo(_tag, "landscape")
      } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
         LogInfo(_tag, "portrait")
      }
   }

   override fun onWindowFocusChanged(hasFocus: Boolean) {
      LogInfo(_tag, "onWindowFocusChanged() $hasFocus")
      super.onWindowFocusChanged(hasFocus)
   }
}