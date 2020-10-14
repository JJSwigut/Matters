package com.jjswigut.matters.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "matter_table")
data class Matter(

    @ColumnInfo(name = "matter_title")
    var matterTitle: String,
    @ColumnInfo(name = "matter_content")
    var matterContent: String,
    @PrimaryKey(autoGenerate = true)
    var matterId: Int = 0
) : Serializable


