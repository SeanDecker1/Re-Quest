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
fun QuestDetailsScreen(questViewModel: QuestViewModel, navController: NavController, questId: String?, userType: String?) {

    var isPlayer = true
    var selectedQuestTasks: State<List<Task>>

    val questtemp = questId ?: "0"
    val quest_id = questtemp.toInt()

    if (userType == "0") {
        isPlayer = true
    } else if (userType == "1") {
        isPlayer = false
    } // Ends userType if

    // If user is a player, they only get to see quests marked as visible
    if (isPlayer) {
        selectedQuestTasks = questViewModel.getAllVisibleTasksByQuestId(quest_id).observeAsState(arrayListOf())
    } else {
        selectedQuestTasks = questViewModel.getAllTasksByQuestId(quest_id).observeAsState(arrayListOf())
    }

    val selectedQuest = questViewModel.getQuestById(quest_id).observeAsState(arrayListOf())

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
            TopBarDetails(currentQuest = selectedQuest, navController = navController, showDialog = showDialog, userType = userType)
        }
    ) {

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
                        TaskRow(task = it, userType = userType, questViewModel = questViewModel)
                    }
                ) // Ends items
            }
        }

    } // Ends Scaffold

} // Ends QuestDetailsScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun QuestDetails(currentQuest: Quest, navController: NavController, questTasks: State<List<Task>>){

    var coverArt = R.drawable.dragon

    if (currentQuest.quest_theme == "Dungeon") {
        coverArt = R.drawable.dragon
    } else if (currentQuest.quest_theme == "Castle") {
        coverArt = R.drawable.castle
    } else if (currentQuest.quest_theme == "Nature") {
        coverArt = R.drawable.tree
    }

        Column(modifier = Modifier
            .fillMaxSize(),
        ) {

            Box(modifier = Modifier
//                .border(width = 2.dp, color = Color.Black)
            ) {
                Image(
                    painter = painterResource(id = coverArt),
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
fun TaskRow(task: Task, userType: String?, questViewModel: QuestViewModel){

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

            if (userType == "1") {

                IconButton(
                    onClick = {
                          questViewModel.deleteTaskById(task.task_id)
                    },
                ) {
                    // Inner content including an icon and a text label
                    Icon(
                        Icons.Outlined.Delete,
                        contentDescription = "Delete",
                    ) // Ends Icon
                } // Ends Button

            } // Ends userType if

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
fun TopBarDetails(currentQuest: State<List<Quest>>, navController: NavController, showDialog: MutableState<Boolean>, userType: String?){

    var isPlayer = true
    var insideUserType = "player"
    if (userType == "0") {
        isPlayer = true
        insideUserType = "player"
    } else if (userType == "1") {
        isPlayer = false
        insideUserType = "gamemaster"
    } // Ends userType if

    TopAppBar(
//        title = {
//            if (currentQuest != null) {
//                currentQuest.quest_name?.let { Text(text = it, fontSize = 18.sp) }
//            }
//        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate(Screens.QuestListScreen.withStringArgs(insideUserType)) {
                    popUpTo(Screens.QuestListScreen.route){
                        inclusive = true
                    }
                } // Ends nav
            }) {
                Icon(Icons.Filled.ArrowBack, "")
            }
        },
        actions = {

            if (!isPlayer) {
                IconButton(onClick = {
                    showDialog.value = true
                }) {
                    Icon(Icons.Rounded.Add, "")
                }
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