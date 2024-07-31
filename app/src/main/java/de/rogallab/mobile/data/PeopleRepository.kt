package de.rogallab.mobile.data

import de.rogallab.mobile.data.database.IDataStore
import de.rogallab.mobile.domain.IPeopleRepository
import de.rogallab.mobile.domain.ResultData
import de.rogallab.mobile.domain.entities.Person
import java.util.Locale
import java.util.UUID
import kotlin.random.Random

class PeopleRepository(
   val dataStore: IDataStore
): IPeopleRepository {



   override fun getAll(): ResultData<MutableList<Person>> {
      return try {
         ResultData.Success(dataStore.selectAll())
      } catch (t: Throwable) {
         ResultData.Error(t)
      }
   }

   override fun getWhere(predicate: (Person) -> Boolean): ResultData<MutableList<Person>> {
      return try {
         ResultData.Success(dataStore.selectWhere(predicate))
      } catch (t: Throwable) {
         ResultData.Error(t)
      }
   }

   override fun getById(id: UUID): ResultData<Person?> {
      return try {
         ResultData.Success(dataStore.findById(id))
      } catch (t: Throwable) {
         ResultData.Error(t)
      }
   }

   override fun getBy(predicate: (Person) -> Boolean): ResultData<Person?> {
      return try {
         ResultData.Success(dataStore.findBy(predicate))
      } catch (t: Throwable) {
         ResultData.Error(t)
      }
   }

   override fun create(person: Person): ResultData<Unit> {
      return try {
         dataStore.insert(person)
         ResultData.Success(Unit)
      } catch (t: Throwable) {
         ResultData.Error(t)
      }
   }

   override fun update(person: Person): ResultData<Unit> {
      return try {
         dataStore.update(person)
         ResultData.Success(Unit)
      } catch (t: Throwable) {
         ResultData.Error(t)
      }
   }

   override fun remove(id: UUID): ResultData<Unit> {
      return try {
         dataStore.delete(id)
         ResultData.Success(Unit)
      } catch (t: Throwable) {
         ResultData.Error(t)
      }
   }

   override fun readDataStore(): ResultData<Unit> {
      return try {
         dataStore.readDataStore()
         ResultData.Success(Unit)
      } catch (t: Throwable) {
         ResultData.Error(t)
      }
   }
   override fun writeDataStore(): ResultData<Unit> {
      return try {
         dataStore.writeDataStore()
         ResultData.Success(Unit)
      } catch (t: Throwable) {
         ResultData.Error(t)
      }
   }

}