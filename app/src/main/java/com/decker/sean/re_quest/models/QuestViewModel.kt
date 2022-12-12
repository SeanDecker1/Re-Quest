package com.decker.sean.re_quest.models

import android.app.Application
import androidx.lifecycle.*
import com.decker.sean.re_quest.data.entities.Quest
import com.decker.sean.re_quest.data.entities.Task
import com.decker.sean.re_quest.data.repositories.QuestRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class QuestViewModel(appObj: Application): AndroidViewModel(appObj) {

    private val questRepository: QuestRepository = QuestRepository(appObj)

    fun getAllQuests(): LiveData<List<Quest>> {
        return questRepository.getAllQuests
    } // Ends getAllQuests

    // Get a list of all quests that are set to visible (for players)
    fun getAllVisibleQuests(): LiveData<List<Quest>> {
        return questRepository.getAllVisibleQuests
    } // Ends getAllVisibleQuests


    fun getQuestById(quest_id: Int): LiveData<List<Quest>> {
        return questRepository.getQuestById(quest_id)
    } // Ends getQuestById

    // Add a quest to the db
    fun insertQuest(quest: Quest) {
        viewModelScope.launch {
            questRepository.insertQuest(quest = quest)
        } // Ends viewModelScope.launch
    } // Ends insertQuest

    // Delete a quest from the db
    fun deleteQuestById(quest_id: Int) {
        viewModelScope.launch {
            questRepository.deleteQuestById(quest_id = quest_id)
        } // Ends viewModelScope.launch
    } // Ends deleteQuestById

    //
    // TASK FUNCTIONS
    //

    // Get a list of all tasks for a quest (for game master)
    fun getAllTasksByQuestId(task_quest: Int): LiveData<List<Task>> {
        return questRepository.getAllTasksByQuestId(task_quest)
    } // Ends getAllTasksByQuestId

//    fun getAllTasksByQuestId(task_quest: Int) {
//        viewModelScope.launch {
//            questRepository.getAllTasksByQuestId(task_quest)
//        }
//    } // Ends getAllTasksByQuestId

    // Get a list of all tasks for a quest that are set to visible (for player)
//    fun getAllVisibleTasksByQuestId(task_quest: Int) = viewModelScope.launch {
//        questRepository.getAllVisibleTasksByQuestId(task_quest)
//    }
    fun getAllVisibleTasksByQuestId(task_quest: Int) {
        viewModelScope.launch {
            questRepository.getAllVisibleTasksByQuestId(task_quest)
        }
    } // getAllVisibleTasksByQuestId


    // Add a task to the db
    fun insertTask(task: Task) {
        viewModelScope.launch {
            questRepository.insertTask(task = task)
        } // Ends viewModelScope.launch
    } // Ends insertTask

    // Delete a task from the db
    fun deleteTaskById(task_id: Int) {
        viewModelScope.launch {
            questRepository.deleteTaskById(task_id = task_id)
        } // Ends viewModelScope.launch
    } // Ends deleteTaskById

    // Delete a task from the db
    fun deleteTaskByQuestId(task_quest: Int) {
        viewModelScope.launch {
            questRepository.deleteTaskByQuestId(task_quest = task_quest)
        } // Ends viewModelScope.launch
    } // Ends deleteTaskByQuestId

} // Ends QuestViewModel