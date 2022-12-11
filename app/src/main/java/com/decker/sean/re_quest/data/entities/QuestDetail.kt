package com.decker.sean.re_quest.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questDetail")
data class QuestDetail(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "detailName")
    val detailName: String?,

    @ColumnInfo(name = "detailDescription")
    val detailDescription: String?,

    @ColumnInfo(name = "isVisible")
    val isVisible: Boolean = true
)
