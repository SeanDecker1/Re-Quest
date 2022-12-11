package com.decker.sean.re_quest.data.repositories

import android.app.Application
import com.decker.sean.re_quest.data.AppDatabase
import com.decker.sean.re_quest.data.dao.QuestDao

class QuestRepository(application: Application) {

    private var questDao: QuestDao

    init {
        val database = AppDatabase.getDatabase(application)
        questDao = database.questDao()
    } // Ends init

} // Ends QuestRepository