

package com.jjswigut.matters.database

import androidx.room.*

@Dao
interface MatterDataBaseDao {

    @Insert
    suspend fun insert(matter: Matter)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(matter: Matter)

    @Delete
    suspend fun delete(matter: Matter)

    @Query("SELECT * FROM matter_table ORDER BY matterId DESC")
    suspend fun getAllMatters(): List<Matter>

    @Query("DELETE FROM matter_table")
    suspend fun clear()


}
