package com.decker.sean.re_quest.screens.questdetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HistoryEdu
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.decker.sean.re_quest.R
import com.decker.sean.re_quest.composable.AddQuestDialog
import com.decker.sean.re_quest.composable.AddQuestTaskDialog
import com.decker.sean.re_quest.data.dao.QuestDao
import com.decker.sean.re_quest.data.entities.Quest
import com.decker.sean.re_quest.models.QuestViewModel
import com.decker.sean.re_quest.navigation.Screens
import com.decker.sean.re_quest.screens.questlist.QuestCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
//fun QuestDetailsScreen(questName: String?) {
fun QuestDetailsScreen(questViewModel: QuestViewModel, navController: NavController, questId: String?) {

    val questtemp = questId ?: "0"
    val quest_id = questtemp.toInt()

    val selectedQuest = questViewModel.getQuestById(quest_id).observeAsState(arrayListOf())
    //val test = selectedQuest.value
    val selectedQuestTasks = questViewModel.getAllTasksByQuestId(quest_id).observeAsState(arrayListOf())

    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        AddQuestTaskDialog(
            questViewModel = questViewModel,
            setShowDialog = { showDialog.value = it },
            currentQuestId = quest_id
        ) // Ends AddQuestTask call
    } // Ends showDialog if

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        topBar = { TopBar() },
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Tasks",
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            ) // Ends Text

            Button(
                onClick = { showDialog.value = true },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White, contentColor = Color.Black),
                modifier = Modifier.size(width = 45.dp, height = 45.dp),
                contentPadding = PaddingValues(all = 0.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp
                )
            ) {
                // Inner content including an icon and a text label
                Icon(
                    Icons.Rounded.Add,
                    contentDescription = "Add New Task",
                    modifier = Modifier
                        .padding(0.dp)
                        .size(24.dp)
                ) // Ends Icon
            } // Ends Button

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 75.dp, start = 8.dp),
            ) {
                items(
                    items = selectedQuest.value,
                    itemContent = {
                        QuestDetails(currentQuest = it, navController = navController)

                    }
                ) // Ends items
            } // Ends LazyColumn



        }// Ends Row

    } // Ends Scaffold

} // Ends QuestDetailsScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun QuestDetails(currentQuest: Quest, navController: NavController){

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth(.5f)
            .padding(bottom = 20.dp),
        onClick = {
            navController.navigate(Screens.QuestScreen.withArgs(currentQuest.quest_id)) {
                popUpTo(Screens.QuestScreen.route){
                    inclusive = true
                }
            }
        },
        elevation = 3.dp
    ){

        Column(modifier = Modifier
            .height(170.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = currentQuest.quest_name ?: "",
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            ) // Ends Text

            Text(
                text = "${currentQuest.quest_id}",
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            ) // Ends Text

            Text(
                text = "${currentQuest.quest_description}",
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            ) // Ends Text

            Text(
                text = "${currentQuest.quest_visible}",
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            ) // Ends Text

        } // Ends Column

    } // Ends Card

} // Ends QuestDetails

@Composable
fun TopBar(){
    TopAppBar(
        title = {Text(text = "Re-Quest", fontSize = 18.sp)},
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.Black,
    ) // Ends TopAppBar
} // Ends TopBar