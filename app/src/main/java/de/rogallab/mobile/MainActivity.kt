package de.rogallab.mobile

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.rogallab.mobile.ui.taskitem.TaskListScreen
import de.rogallab.mobile.ui.theme.AppTheme

class MainActivity : BaseActivity(TAG) {

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      setContent {

         AppTheme {

            Surface(
               modifier = Modifier.fillMaxSize(),
               color = MaterialTheme.colorScheme.background
            ) {
               Log.d("ok>ComposeView        .", "Composition")
//             LazyColumnTest()
               TaskListScreen()
            }
         }
      }
   }

   companion object {
      const val isInfo = true
      const val isDebug = true
      //12345678901234567890123
      private const val TAG = "ok>MainActivity       ."

   }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
   AppTheme {
      Surface(
         modifier = Modifier.fillMaxSize(),
         color = MaterialTheme.colorScheme.background
      ) {
//       LazyColumnTest()
//         TaskItem1(
//            id = 1,                            // State ↓
//            label = "Task",                      // State ↓
//            onClose = {},              // Event ↑  IconButton
//            onClicked = {}            // Event ↑  TaskItem
//         )

         TaskListScreen()
      }
   }
}