

package com.jjswigut.matters.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MatterDataBaseDao{

    @Insert
    suspend fun insert(matter: Matter)

    @Update
    suspend fun update(matter: Matter)

    @Query("SELECT * from matter_table WHERE matterId = :key")
    suspend fun get(key: Long): Matter?

    @Query("DELETE FROM matter_table")
    suspend fun clear()





}
