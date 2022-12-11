package com.decker.sean.re_quest.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quest")
data class Quest(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "questName")
    val questName: String?,

    @ColumnInfo(name = "questDescription")
    val questDescription: String?,

    @ColumnInfo(name = "questDetails")
    val questDetails: List<QuestDetail>?
)