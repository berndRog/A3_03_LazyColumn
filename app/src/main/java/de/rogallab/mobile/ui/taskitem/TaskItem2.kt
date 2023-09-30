package de.rogallab.mobile.ui.taskitem

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import de.rogallab.mobile.utilities.LogComp
import de.rogallab.mobile.utilities.LogDebug

@Composable
fun TaskItem2(
   id: Int,                            // State ↓
   label: String,                      // State ↓
   onClose: (Int)-> Unit,              // Event ↑  IconButton
   onClicked: (Int) -> Unit            // Event ↑  TaskItem
) {

   val tag: String = "ok>TaskItem           ."
   LogComp(tag, "Task: $id $label")


   var checked: Boolean by rememberSaveable{ mutableStateOf(false) }

   ListItem(
      headlineContent = { Text(text = label)  },
      modifier = Modifier,
      overlineContent = {},
      supportingContent = { Text(text = "2. Zeile") },
      leadingContent = {
         Checkbox(
            checked = checked,                 // State ↓
            onCheckedChange = {                 // Event -> remember
               LogDebug(tag, "Checkbox $id clicked: $it")
               checked = it
            },
         //modifier = Modifier.weight(0.2f)
         )},
      trailingContent = {
         IconButton(
            onClick = { onClose(id) },                // Event ↑
            modifier = Modifier.padding(end = 8.dp)
               //.weight(0.1f)
         ) {
            Icon(
               imageVector = Icons.Filled.Close,
               contentDescription = "Delete item"
            )
         }
      },
      colors = ListItemDefaults.colors(),
      tonalElevation = ListItemDefaults.Elevation,
      shadowElevation = ListItemDefaults.Elevation
   )
   Divider()

}