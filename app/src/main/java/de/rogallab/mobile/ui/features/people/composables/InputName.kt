package de.rogallab.mobile.ui.features.people.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun InputName1(
   name: String,                     // State ↓
   onNameChange: (String) -> Unit,   // Event ↑
   label: String = "Name"
) {
   TextField(
      value = name,                                        // State ↓
      onValueChange = { it: String -> onNameChange(it)  }, // Event ↑
      label = { Text(text = label) },
      singleLine = true,
      modifier = Modifier.padding(top = 16.dp).fillMaxWidth()
   )
}

@Composable
fun InputName(
   name: String,                             // State ↓
   onNameChange: (String) -> Unit,           // Event ↑
   label: String = "Name"
) {
   TextField(
      value = name,                          // State ↓
      onValueChange = { it: String ->
         onNameChange(it)                    // Event ↑
      },
      label = { Text(text = label) },
      singleLine = true,
      isError =  name.length > 20,
      supportingText = {
         if (name.length > 20) {
            Text("Zu lang (maximal 20 Zeichen)")
         }
      },
      modifier = Modifier.padding(top = 16.dp).fillMaxWidth()
         .testTag("nameTextField")
   )
}
