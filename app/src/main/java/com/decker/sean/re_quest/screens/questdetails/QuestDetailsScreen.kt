package com.decker.sean.re_quest.screens.questdetails

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.HistoryEdu
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.decker.sean.re_quest.R
import com.decker.sean.re_quest.composable.AddQuestTaskDialog
import com.decker.sean.re_quest.data.entities.Quest
import com.decker.sean.re_quest.data.entities.Task
import com.decker.sean.re_quest.models.QuestViewModel
import com.decker.sean.re_quest.navigation.Screens
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

        topBar = {
            TopBarDetails(currentQuest = selectedQuest, navController = navController, showDialog = showDialog)
        }
    ) {

//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//
//            Text(
//                text = "Tasks",
//                fontSize = MaterialTheme.typography.h4.fontSize,
//                fontWeight = FontWeight.Bold
//            ) // Ends Text
//
//            IconButton(
//                onClick = { showDialog.value = true },
//            ) {
//                // Inner content including an icon and a text label
//                Icon(
//                    Icons.Rounded.Add,
//                    contentDescription = "Add New Task",
//                ) // Ends Icon
//            } // Ends Button
//
//
//
//        }// Ends Row

        Column() {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp),
            ) {
                items(
                    items = selectedQuest.value,
                    itemContent = {
                        QuestDetails(currentQuest = it, navController = navController, questTasks = selectedQuestTasks)
//                        TopBarDetails(currentQuest = it)

                    }
                ) // Ends items
            } // Ends LazyColumn

            LazyColumn(){
                items(
                    items = selectedQuestTasks.value,
                    itemContent = {
                        TaskRow(task = it)
                    }
                ) // Ends items
            }
        }




    } // Ends Scaffold

} // Ends QuestDetailsScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun QuestDetails(currentQuest: Quest, navController: NavController, questTasks: State<List<Task>>){

        Column(modifier = Modifier
            .fillMaxSize(),
        ) {

            Box(modifier = Modifier
//                .border(width = 2.dp, color = Color.Black)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dragon),
                    contentDescription = "Cover",
                    modifier = Modifier
                        .height(130.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                ) // Ends Image
            } // Ends Box
            Column(modifier = Modifier.padding(top = 16.dp, start = 8.dp, end = 8.dp)) {
                Text(
                    text = "${currentQuest.quest_description}",
                    fontSize = MaterialTheme.typography.body1.fontSize,
                    fontWeight = FontWeight.Light
                ) // Ends Text

                Spacer(modifier = Modifier.padding(16.dp))

            }



        } // Ends Column

} // Ends QuestDetails

@Composable
fun TaskRow(task: Task){

    Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Start
            ){
                Icon(
                    Icons.Outlined.Article,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 12.dp)
                ) // Ends Icon
                task.task_name?.let { Text(text = it) }
            }

            IconButton(
                onClick = { /*TODO*/ },
            ) {
                // Inner content including an icon and a text label
                Icon(
                    Icons.Outlined.Delete,
                    contentDescription = "Delete",
                ) // Ends Icon
            } // Ends Button
        }
        task.task_description?.let {
            Text(
                text = it,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )

        }
        Divider()
    }

}

@Composable
fun TopBarDetails(currentQuest: State<List<Quest>>, navController: NavController, showDialog: MutableState<Boolean>){
    TopAppBar(
//        title = {
//            if (currentQuest != null) {
//                currentQuest.quest_name?.let { Text(text = it, fontSize = 18.sp) }
//            }
//        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate(Screens.QuestListScreen.route){
                    popUpTo(Screens.QuestListScreen.route){
                        inclusive = true
                    }
                }
            }) {
                Icon(Icons.Filled.ArrowBack, "")
            }
        },
        actions = {
            IconButton(onClick = {
                showDialog.value = true
            }) {
                Icon(Icons.Rounded.Add, "")
            }
        },
        title = {
            LazyColumn(

            ) {
                items(
                    items = currentQuest.value,
                    itemContent = {
                        Text(text = getName(it))

                    }
                ) // Ends items
            } // Ends LazyColumn
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.Black,
    ) // Ends TopAppBar
} // Ends TopBar

fun getName(currentQuest: Quest): String {
    return "Quest: ${currentQuest.quest_name.toString()}"

}