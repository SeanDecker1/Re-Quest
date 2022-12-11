package com.decker.sean.re_quest.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.remember
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.decker.sean.re_quest.data.entities.Quest
import com.decker.sean.re_quest.models.QuestViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun QuestList(questViewModel: QuestViewModel, navController: NavController) {

    val questList = questViewModel.getAllQuests().observeAsState(arrayListOf())
    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        AddQuestDialog(
            questViewModel = questViewModel,
            setShowDialog = { showDialog.value = it }
        ) // Ends AddQuestDialog call
    } // Ends showDialog if

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Add Quest", color = Color.White) },
                onClick = { showDialog.value = true },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Icon",
                        tint = Color.White
                    )
                } // Ends Icon
            ) // Ends ExtendedFloatingActionButton
        }, // Ends floatingActionButton
        content = {

            LazyColumn(content = {

                items(
                    items = questList.value,
                    itemContent = {
                        QuestRow(quest = it, questViewModel = questViewModel, navController = navController)
                    }
                ) // Ends items

            }) // Ends LazyColumn

        } // Ends content
    ) // Ends Scaffold

} // Ends QuestList

@Composable
fun QuestRow(quest: Quest, questViewModel: QuestViewModel, navController: NavController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        // Add below to be able to click the quest
            //.clickable { questViewModel.onQuestClicked(quest); navController.navigate("questlist") }
        content = {

            // This val determines the text color of the name
            val color = MaterialTheme.colors.primary

            Column(
                modifier = Modifier.weight(2F),
                content = {

                    Spacer(modifier = Modifier.size(8.dp))

                    // Quest name text
                    Text(
                        text = quest.quest_name ?: "",
                        color = color,
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    ) // Ends quest name text

                    // Quest ID text
                    Text(
                        text = "${quest.quest_id}",
                        color = Color.Black,
                        fontSize = 16.sp
                    ) // Ends quest id text

                    // Quest Completed text
                    if (quest.quest_completed == 1) {
                        Text(
                            text = "COMPLETED",
                            color = Color.Green,
                            fontSize = 16.sp
                        ) // Ends quest completed text
                    } else if (quest.quest_completed == 0) {
                        Text (
                            text = "NOT COMPLETED",
                            color = Color.Gray,
                            fontSize = 16.sp
                        ) // Ends quest not completed text
                    } // Ends if

                } // Ends column content
            ) // Ends column

            Spacer(modifier = Modifier.size(16.dp))

            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Color.Red,
                modifier = Modifier
                    .size(30.dp)
                    .clickable(onClick = {
                        questViewModel.deleteQuestById(quest.quest_id)
                    }) // Ends .clickable
            ) // Ends icon

        } // Ends row content

    ) // Ends Row

} // Ends QuestRow