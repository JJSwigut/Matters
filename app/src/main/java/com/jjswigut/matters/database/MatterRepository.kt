package com.jjswigut.matters.database

import androidx.lifecycle.LiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MatterRepository @Inject constructor(
    private val dao: MatterDataBaseDao
) {

    val allMatters: LiveData<List<Matter>> = dao.getAllMatters()

    suspend fun insert(matter: Matter) {
        dao.insert(matter)
    }

    suspend fun delete(matter: Matter) {
        dao.delete(matter)
    }

    suspend fun update(matter: Matter) {
        dao.update(matter)
    }


}