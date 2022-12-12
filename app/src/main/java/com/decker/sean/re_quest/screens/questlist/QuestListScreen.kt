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
import androidx.compose.material.icons.rounded.AccountCircle
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun QuestListScreen(questViewModel: QuestViewModel, navController: NavController) {

    //dummy View Model for now
    val questListViewModel = arrayListOf(listOf("My First Quest", R.drawable.tree), listOf("Dungeon Quest 2", R.drawable.dragon), listOf("Castle Raid", R.drawable.grimes_art))

    // Real view model
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
            .fillMaxSize()
            .background(Color.White),
        topBar = { TopBar() },
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

            IconButton(
                onClick = { showDialog.value = true },
            ) {
                // Inner content including an icon and a text label
                Icon(
                    Icons.Rounded.Add,
                    contentDescription = "Add New Quest",
                ) // Ends Icon
            } // Ends Button
        }// Ends Row

        Spacer(modifier = Modifier.padding(36.dp))

        //
        // TEMP: Uncomment to view raw/basic list
        //
        //QuestList(questViewModel = questViewModel, navController = navController)
        //
        // TEMP
        //

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 75.dp, start = 8.dp, end = 8.dp),
        ) {
            items(
                // Switched with uncommented version
                //items = questListViewModel,
                items = questList.value,
                itemContent = {
                    //QuestCard(currentQuest = it[0].toString(), navController = navController, coverArt = it[1] as Int)
                    QuestCard(currentQuest = it, navController = navController, coverArt = R.drawable.tree)
                }
            ) // Ends items
        } // Ends LazyColumn

    } // Ends Scaffold

} // Ends QuestListScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
// Switched with uncommented version
//fun QuestCard(currentQuest: String, navController: NavController, coverArt: Int){
fun QuestCard(currentQuest: Quest, navController: NavController, coverArt: Int){

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        onClick = {
            // Switched with uncommented version
            //navController.navigate(Screens.QuestScreen.withArgs(currentQuest)) {
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
                    .background(color = Color.White),
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
                    // Switched with uncommented version
                    //text = currentQuest,
                    text = currentQuest.quest_name ?: "",
                    modifier = Modifier.padding(5.dp),
                    style = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ) // Ends style
                ) // Ends Text
            } // Ends Row

        } // Ends Column

    } // Ends Card

} // Ends QuestCard

@Composable
fun TopBar(){
    TopAppBar(


        actions = {
            IconButton(onClick = {

            }) {
                Icon(Icons.Filled.AccountCircle, "")
            }
        },

        title = {Text(text = "Re-Quest", fontSize = 18.sp)},
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.Black,
    ) // Ends TopAppBar
} // Ends TopBar