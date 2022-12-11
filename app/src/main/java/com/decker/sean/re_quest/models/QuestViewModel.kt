package com.decker.sean.re_quest.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.ui.viewmodel.viewModel
import com.decker.sean.re_quest.data.entities.Quest
import com.decker.sean.re_quest.data.entities.Task
import com.decker.sean.re_quest.data.repositories.QuestRepository
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
//    fun getAllTasksByQuestId(task_quest: Int): LiveData<List<Task>> {
//
//        var taskData: LiveData<List<Task>>
//        viewModelScope.launch {
//            val taskDataResponse = questRepository.getAllTasksByQuestId(task_quest = task_quest)
//
//            taskData = Transformations.map(taskDataResponse) { taskResponse ->
//                taskResponse
//            }
//
//        }
//
//        taskData.observe(viewModelScope.Observer { taskData ->
//
//        })
//
//    } // Ends getAllTasksByQuestId

    // Get a list of all tasks for a quest that are set to visible (for player)

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