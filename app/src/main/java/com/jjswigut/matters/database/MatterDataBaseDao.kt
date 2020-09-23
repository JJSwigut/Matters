

package com.jjswigut.matters.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MatterDataBaseDao{

    @Insert
    suspend fun insert(matter: Matter)

    @Insert
    suspend fun update(matter: Matter)

    @Query("SELECT * from matter_table WHERE matterId = :key")
    suspend fun get(key: Long): Matter?

    @Query("DELETE FROM matter_table")
    suspend fun clear()

    @Query("SELECT * FROM matter_table ORDER BY matterId DESC LIMIT 1 ")
    suspend fun getMatter(): Matter?




}
