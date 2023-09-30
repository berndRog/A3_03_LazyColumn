package de.rogallab.mobile.ui.taskitem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import de.rogallab.mobile.utilities.logDebug

@Composable
fun TaskItem1(
   id: Int,                           // State ↓
   label: String,                     // State ↓
   onClose: (Int)-> Unit,             // Event ↑  IconButton
   onClicked: (Int) -> Unit           // Event ↑  TaskItem
) {

   val tag: String = "ok>TaskItem           ."
   logDebug(tag, "Task: $id $label")

   var checked: Boolean by rememberSaveable{ mutableStateOf(false) }

   Column() {

      Divider()

      Row(
         verticalAlignment = Alignment.CenterVertically,
         modifier = Modifier.padding(vertical = 16. dp)
            .clickable {
               onClicked(id) }        // Event ↑
            .testTag("CloseButton")
      ) {

         Checkbox(
            checked = checked ,       // State ↓
            onCheckedChange = { it -> // Event -> remember
               logDebug(tag, "Checkbox $id clicked: $it")
               checked = it
            },
            modifier = Modifier.weight(0.2f)
               .testTag("CheckBox1")
         )

         Column(modifier = Modifier.weight(0.7f) ) {
            Text(
               text = label,                             // State ↓
               style = MaterialTheme.typography.bodyLarge,
               modifier = Modifier.padding(start = 16.dp)
            )
            Text(
               text = "2. Zeile",                             // State ↓
               style = MaterialTheme.typography.bodyMedium,
               modifier = Modifier.padding(start = 16.dp)
            )
         }

         IconButton(
            onClick = {  onClose(id) },                // Event ↑
            modifier = Modifier.padding(end = 16.dp)
               .weight(0.1f)
         ) {
            Icon(
               imageVector = Icons.Filled.Close,
               contentDescription = "Delete item"
            )
         }
      }

   }
}