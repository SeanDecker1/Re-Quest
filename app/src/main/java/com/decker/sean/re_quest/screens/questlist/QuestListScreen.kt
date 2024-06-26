package com.decker.sean.re_quest.screens.questlist

import android.annotation.SuppressLint

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.outlined.HistoryEdu
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image

import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.rounded.AccountCircle

import androidx.compose.runtime.State

import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp
import com.decker.sean.re_quest.R
import com.decker.sean.re_quest.composable.AddQuestDialog
import com.decker.sean.re_quest.data.entities.Quest
import com.decker.sean.re_quest.models.QuestViewModel
import com.decker.sean.re_quest.navigation.Screens
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun QuestListScreen(questViewModel: QuestViewModel, navController: NavController, userType: String?) {

    var isPlayer = true
    var insideUserType = 0
    var questList: State<List<Quest>>

    if (userType == "player") {
        isPlayer = true
        insideUserType = 0
    } else if (userType == "gamemaster") {
        isPlayer = false
        insideUserType = 1
    } // Ends userType if

    // If user is a player, they only get to see quests marked as visible
    if (isPlayer) {
        questList = questViewModel.getAllVisibleQuests().observeAsState(arrayListOf())
    } else {
        questList = questViewModel.getAllQuests().observeAsState(arrayListOf())
    }

    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        AddQuestDialog(
            questViewModel = questViewModel,
            setShowDialog = { showDialog.value = it }
        ) // Ends AddQuestDialog call
    } // Ends showDialog if

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        topBar = { TopBar(navController) },
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 8.dp, end = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Quests",
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            ) // Ends Text

            // Is user is a game master, they can see this add button
            if (!isPlayer) {
                IconButton(
                    onClick = { showDialog.value = true },
                ) {
                    // Inner content including an icon and a text label
                    Icon(
                        Icons.Rounded.Add,
                        contentDescription = "Add New Quest",
                    ) // Ends Icon
                } // Ends Button
            } // Ends if

        }// Ends Row

        Spacer(modifier = Modifier.padding(36.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 75.dp, start = 8.dp, end = 8.dp),
        ) {
            items(
                items = questList.value,
                itemContent = {
                    QuestCard(currentQuest = it, navController = navController, userType = insideUserType, questViewModel = questViewModel)
                }
            ) // Ends items
        } // Ends LazyColumn

    } // Ends Scaffold

} // Ends QuestListScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun QuestCard(currentQuest: Quest, navController: NavController, userType: Int, questViewModel: QuestViewModel){

    var coverArt = R.drawable.dragon

    if (currentQuest.quest_theme == "Dungeon") {
        coverArt = R.drawable.dragon
    } else if (currentQuest.quest_theme == "Castle") {
        coverArt = R.drawable.castle
    } else if (currentQuest.quest_theme == "Nature") {
        coverArt = R.drawable.tree
    }

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        onClick = {
            navController.navigate(Screens.QuestScreen.withArgs(currentQuest.quest_id, userType)) {
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

            Box(modifier = Modifier
//                .border(width = 2.dp, color = Color.Black)
            ) {
                Image(
                    painter = painterResource(id = coverArt),
                    contentDescription = "Cover",
                    modifier = Modifier
                        .height(120.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                ) // Ends Image
            } // Ends Box

            Row(
                modifier = Modifier
                    .height(50.dp)
                    .padding(8.dp)
                    .background(color = Color.White)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Outlined.HistoryEdu,
                        contentDescription = "Quest",
                        modifier = Modifier
                            .padding(0.dp)
                            .size(30.dp),
                    ) // Ends Icon

                    Text(
                        text = currentQuest.quest_name ?: "",
                        modifier = Modifier.padding(5.dp),
                        style = MaterialTheme.typography.button.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ) // Ends style
                    ) // Ends Text
                } // Ends Row

                if (userType == 1) {
                    IconButton(
                        onClick = { questViewModel.deleteQuestById(currentQuest.quest_id) },
                    ) {
                        // Inner content including an icon and a text label
                        Icon(
                            Icons.Outlined.Delete,
                            contentDescription = "Delete",
                        ) // Ends Icon
                    } // Ends Button
                } // Ends if

            }//Ends Card Info Row

        } // Ends Column

    } // Ends Card

} // Ends QuestCard

@Composable
fun TopBar(navController: NavController){
    TopAppBar(
        actions = {
            IconButton(onClick = {
                navController.navigate(Screens.LoginScreen.route)
            }) {
                Icon(Icons.Filled.AccountCircle, "")
            }
        },
        title = {Text(text = "Re-Quest", fontSize = 18.sp)},
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.Black,
    ) // Ends TopAppBar
} // Ends TopBar