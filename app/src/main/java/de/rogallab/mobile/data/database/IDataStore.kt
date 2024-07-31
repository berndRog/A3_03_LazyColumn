package de.rogallab.mobile.data.database

import de.rogallab.mobile.domain.entities.Person
import java.util.UUID

interface IDataStore {
   fun selectAll()
      : MutableList<Person>
   fun selectWhere(predicate: (Person) -> Boolean)
      : MutableList<Person>
   fun findById(id: UUID)
      : Person?
   fun findBy(predicate: (Person) -> Boolean)
      : Person?

   fun insert(person: Person)
   fun update(personToUpdate: Person)
   fun delete(id: UUID)

   fun readDataStore()
   fun writeDataStore()
}