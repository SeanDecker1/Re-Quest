package com.decker.sean.re_quest.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quest")
data class Quest(

    @PrimaryKey(autoGenerate = true)
    val quest_id: Int = 0,

    @ColumnInfo(name = "quest_name")
    val quest_name: String?,

    @ColumnInfo(name = "quest_description")
    val quest_description: String?,

    @ColumnInfo(name = "quest_visible")
    val quest_visible: Int?,

    @ColumnInfo(name = "quest_completed")
    val quest_completed: Int?,

) // Ends data class