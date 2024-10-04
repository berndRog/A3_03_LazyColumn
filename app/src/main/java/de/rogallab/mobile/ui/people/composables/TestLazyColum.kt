package de.rogallab.mobile.ui.people.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.rogallab.mobile.domain.entities.Person

@Composable
fun TestLazyColumn() {

   val people = remember {
      mutableStateListOf(
         Person("Achim", "Arndt"),
         Person("Beate", "Bauer"),
         Person("Christine", "Conrad"),
         Person("Dorothee", "Dietrichs")
      )
   }

   LazyColumn(
      state = rememberLazyListState(),
      contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
   ) {
      // Single item
      item {
         HorizontalDivider()
         Text(
            text = "Single Item",
            modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()
         )
         HorizontalDivider()
      }

      // multiple items addressed by index
      items(
         count = people.size,
      ) { index ->
         val person = people[index]
         Column( modifier = Modifier.fillMaxWidth() ) {
            Text(
               text = "$index",
               modifier = Modifier.padding(top = 8.dp)
            )
            Text(
               text = "${person.firstName} ${person.lastName}",
               modifier = Modifier.padding(bottom = 8.dp))
            HorizontalDivider()
         }

      }

      // multiple items with key
      items(
         items = people,
         key = { person -> person.id }
      ) { person ->
         Column( modifier = Modifier.fillMaxWidth() ) {
            Text(
               text = person.id,
               modifier = Modifier.padding(top = 8.dp)
            )
            Text(
               text = "${person.firstName} ${person.lastName}",
               modifier = Modifier.padding(bottom = 8.dp))
            HorizontalDivider()
         }
      }

   }
}
