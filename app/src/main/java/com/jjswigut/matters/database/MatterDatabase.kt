

package com.jjswigut.matters.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Matter::class],version = 1, exportSchema = false)
abstract class MatterDatabase:RoomDatabase(){

    abstract val matterDataBaseDao: MatterDataBaseDao

    companion object {

        @Volatile
        private var INSTANCE: MatterDatabase? = null

        fun getInstance(context:Context): MatterDatabase {
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,
                    MatterDatabase::class.java,
                    "matters_database")
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}
