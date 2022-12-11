package com.decker.sean.re_quest.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.decker.sean.re_quest.data.entities.Quest
import com.decker.sean.re_quest.data.entities.Task

@Dao
interface QuestDao {

    //
    // QUEST FUNCTIONS
    //

    // Get a list of all quests (for game master)
    @Query("SELECT * FROM quest")
    fun getAllQuests(): LiveData<List<Quest>>

    // Get a list of all quests that are set to visible (for players)
    @Query("SELECT * FROM quest WHERE quest_visible = 1")
    fun getAllVisibleQuests(): LiveData<List<Quest>>

    // Add a quest to the db
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuest(quest: Quest)

    // Delete a quest from the db
    @Query("DELETE FROM quest WHERE quest_id = :quest_id")
    suspend fun deleteQuestById(quest_id: Int)

    //
    // TASK FUNCTIONS
    //

    // Get a list of all tasks for a quest (for game master)
    @Query("SELECT * FROM task WHERE task_quest = :task_quest")
    fun getAllTasksByQuestId(task_quest: Int): LiveData<List<Task>>

    // Get a list of all tasks for a quest that are set to visible (for player)
    @Query("SELECT * FROM task WHERE task_quest = :task_quest AND task_visible = 1")
    fun getAllVisibleTasksByQuestId(task_quest: Int): LiveData<List<Task>>

    // Add a task to the db
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    // Delete a task from the db
    @Query("DELETE FROM task WHERE task_id = :task_id")
    suspend fun deleteTaskById(task_id: Int)

    // Delete a task from the db
    @Query("DELETE FROM task WHERE task_quest = :task_quest")
    suspend fun deleteTaskByQuestId(task_quest: Int)

} // Ends QuestDao