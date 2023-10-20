package de.rogallab.mobile.ui.tasks

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import de.rogallab.mobile.model.Task
import de.rogallab.mobile.utilities.logDebug

class TaskViewModel : ViewModel() {

   private var _number: Int = 0

   // properties
   val tasks: SnapshotStateList<Task>  = mutableStateListOf()

   init {
      for (i in 0..29) add()
   }

   fun get(id: Int): Task? =
      tasks.firstOrNull { task -> task.id == id}

   fun add() {
      _number++
      val newTask = Task(id = _number, label = "Task # $_number")
      tasks.add(newTask)
      logDebug(tag,"Add $newTask")
   }

   fun remove(id: Int) =
      get(id)?.let { it: Task ->
         tasks.remove(it)
         logDebug(tag,"Remove $it")
      }
   companion object {
      // elds                  12345678901234567890123
      const val tag: String = "ok>TaskViewModel      ."
   }

}