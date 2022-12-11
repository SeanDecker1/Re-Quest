package com.decker.sean.re_quest.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(

    @PrimaryKey(autoGenerate = true)
    val task_id: Int = 0,

    @ColumnInfo(name = "task_name")
    val task_name: String?,

    @ColumnInfo(name = "task_description")
    val task_description: String?,

    // quest_id of the applicable quest
    @ColumnInfo(name = "task_quest")
    val task_quest: Int?,

    @ColumnInfo(name = "task_visible")
    val task_visible: Int?,

    @ColumnInfo(name = "task_completed")
    val task_completed: Int?

) // Ends data class