package de.rogallab.mobile.ui.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun LazyColumnTest() {

   val people = remember {
      mutableStateListOf(
         Person(firstName = "Achim", lastName = "Arndt"),
         Person(firstName = "Beate", lastName = "Bauer"),
         Person(firstName = "Christine", lastName = "Conrad"),
         Person(firstName = "Dorothee", lastName = "Dietrichs")
      )
   }

   LazyColumn(
      state = rememberLazyListState(),
      contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
   ) {
      // LayList Scope block

      // Single item
      item {
         Divider()
         Text(
            text = "Single Item",
            modifier = Modifier.padding(vertical = 16.dp)
               .fillMaxWidth()
         )
         Divider()
      }

      // multiple items
      items(people) { person ->
         Text(text = "${person.firstName} ${person.lastName}",
            modifier = Modifier.padding(vertical = 16.dp)
               .fillMaxWidth()
         )
         Divider()
      }


      // multiple items
      items(
         items = people,
         key = { person -> person.id }
      ) { person ->
         Column(
            modifier = Modifier.fillMaxWidth()
         ) {
            Text(
               text = person.id.toString(),
               modifier = Modifier.padding(top = 16.dp)
            )
            Text(
               text = "${person.firstName} ${person.lastName}",
               modifier = Modifier.padding(bottom = 16.dp)
            )
            Divider()
         }
      }
   }
}

data class Person(
   val id: UUID = UUID.randomUUID(),
   val firstName: String = "",
   val lastName: String = ""
)
