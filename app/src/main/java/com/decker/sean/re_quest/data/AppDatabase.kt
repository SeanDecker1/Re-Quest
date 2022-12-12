package com.decker.sean.re_quest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.decker.sean.re_quest.data.dao.QuestDao
import com.decker.sean.re_quest.data.entities.Quest
import com.decker.sean.re_quest.data.entities.Task

@Database(entities = [Quest::class, Task::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun questDao(): QuestDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            } // Ends if

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "quests")
                    .build()
                INSTANCE = instance
                return instance
            } // Ends synchronized

        } // Ends getDatabase

    } // Ends companion object

} // Ends AppDatabase