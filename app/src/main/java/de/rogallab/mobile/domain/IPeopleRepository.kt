package de.rogallab.mobile.domain

import de.rogallab.mobile.domain.entities.Person
import java.util.UUID

interface IPeopleRepository {

   fun getAll()
      : ResultData<MutableList<Person>>
   fun getWhere(predicate: (Person) -> Boolean)
      : ResultData<MutableList<Person>>
   fun getById(id: UUID)
      : ResultData<Person?>
   fun getBy(predicate: (Person) -> Boolean)
      : ResultData<Person?>

   fun create(person: Person): ResultData<Unit>
   fun update(person: Person): ResultData<Unit>
   fun remove(id: UUID): ResultData<Unit>

   fun readDataStore(): ResultData<Unit>
   fun writeDataStore(): ResultData<Unit>

}
