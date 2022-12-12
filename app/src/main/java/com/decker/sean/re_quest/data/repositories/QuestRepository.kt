package com.decker.sean.re_quest.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.decker.sean.re_quest.data.AppDatabase
import com.decker.sean.re_quest.data.dao.QuestDao
import com.decker.sean.re_quest.data.entities.Quest
import com.decker.sean.re_quest.data.entities.Task

class QuestRepository(application: Application) {

    private var questDao: QuestDao

    init {
        val database = AppDatabase.getDatabase(application)
        questDao = database.questDao()
    } // Ends init

    // Get a list of all quests (for game master)
    val getAllQuests: LiveData<List<Quest>> = questDao.getAllQuests()

    // Get a list of all quests that are set to visible (for players)
    val getAllVisibleQuests: LiveData<List<Quest>> = questDao.getAllVisibleQuests()

    // Get one quest by id
    fun getQuestById(quest_id: Int): LiveData<List<Quest>> {
        return questDao.getQuestById(quest_id)
    } // Ends getQuestById

    // Add a quest to the db
    suspend fun insertQuest(quest: Quest) {
        questDao.insertQuest(quest)
    } // Ends insertQuest

    // Delete a quest from the db
    suspend fun deleteQuestById(quest_id: Int) {
        questDao.deleteQuestById(quest_id)
    } // Ends deleteQuestById

    //
    // TASK FUNCTIONS
    //

    // Get a list of all tasks for a quest (for game master)
    //suspend fun getAllTasksByQuestId(task_quest: Int): LiveData<List<Task>> = questDao.getAllTasksByQuestId(task_quest)
    fun getAllTasksByQuestId(task_quest: Int): LiveData<List<Task>> {
        return questDao.getAllTasksByQuestId(task_quest)
    } // Ends getAllTasksByQuestId

    // Get a list of all tasks for a quest that are set to visible (for player)
    //suspend fun getAllVisibleTasksByQuestId(task_quest: Int): LiveData<List<Task>> = questDao.getAllVisibleTasksByQuestId(task_quest)
    fun getAllVisibleTasksByQuestId(task_quest: Int): LiveData<List<Task>> {
        return questDao.getAllVisibleTasksByQuestId(task_quest)
    } // Ends getAllVisibleTasksByQuestId

    // Add a task to the db
    suspend fun insertTask(task: Task) {
        questDao.insertTask(task)
    } // Ends insertTask

    // Delete a task from the db
    suspend fun deleteTaskById(task_id: Int) {
        questDao.deleteTaskById(task_id)
    } // Ends deleteTaskById

    // Delete a task from the db
    suspend fun deleteTaskByQuestId(task_quest: Int) {
        questDao.deleteTaskByQuestId(task_quest)
    } // Ends deleteTaskByQuestId

} // Ends QuestRepository