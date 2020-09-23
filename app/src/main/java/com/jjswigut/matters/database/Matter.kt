

package com.jjswigut.matters.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matter_table")
data class Matter(
        @PrimaryKey(autoGenerate = true)
        var matterId:Long = 0L,

        @ColumnInfo(name = "matter_title")
        val matterTitle:String  ,

        @ColumnInfo(name = "matter_content")
        var matterContent:String  ,


)


