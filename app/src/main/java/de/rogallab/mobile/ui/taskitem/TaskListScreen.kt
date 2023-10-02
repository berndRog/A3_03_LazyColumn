package de.rogallab.mobile.ui.taskitem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.rogallab.mobile.model.Task
import de.rogallab.mobile.ui.TaskViewModel
import de.rogallab.mobile.utilities.logDebug

@Composable
fun TaskListScreen(
   viewModel: TaskViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

   val tag: String = "ok>TaskListScreen     ."
   logDebug(tag, "Start")

   Column {

      Button(
         onClick = { viewModel.add() },
         modifier = Modifier.fillMaxWidth()
            .padding(all = 8. dp)
      ) {
         Text(text="Add a Task")
      }

      TaskList(
         tasks = viewModel.tasks,                     // State ↓
         onClicked = { id ->                          // Event ↑
            viewModel.get(id)?.let{ task: Task ->
               logDebug(tag,"TaskItem $id clicked")
            }
         },
         onClose = { id ->                            // Event ↑
            viewModel.remove(id)
         }
      )
   }
}