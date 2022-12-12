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
    var questList: State<List<Quest>>

    if (userType == "player") {
        isPlayer = true
    } else if (userType == "gamemaster") {
        isPlayer = false
    } // Ends userType if

    // If user is a player, they only get to see quests marked as visible
    if (isPlayer) {
        questList = questViewModel.getAllVisibleQuests().observeAsState(arrayListOf())
    } else {
        questList = questViewModel.getAllQuests().observeAsState(arrayListOf())
    }

    val dummyViewModel = arrayListOf(listOf("My First Quest", R.drawable.tree), listOf("Dungeon Quest 2", R.drawable.dragon), listOf("Castle Raid", R.drawable.grimes_art))

    val coverArtList = arrayListOf(R.drawable.tree, R.drawable.dragon, R.drawable.grimes_art)

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
                .padding(8.dp),
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
                Button(
                    onClick = { showDialog.value = true },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.size(width = 45.dp, height = 45.dp),
                    contentPadding = PaddingValues(all = 0.dp),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp
                    )
                ) {
                    // Inner content including an icon and a text label
                    Icon(
                        Icons.Rounded.Add,
                        contentDescription = "Add New Quest",
                        modifier = Modifier
                            .padding(0.dp)
                            .size(24.dp)
                    ) // Ends Icon
                } // Ends Button
            } // Ends if

        }// Ends Row

        Spacer(modifier = Modifier.padding(36.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 75.dp, start = 8.dp),
            ) {
                items(
                    items = questList.value,
                    itemContent = {

                        val random = Random()
                        val number = random.nextInt(3) //+ 1

                        Row(modifier = Modifier.fillMaxWidth().padding(end = 10.dp)) {
                            QuestCard(
                                currentQuest = it,
                                navController = navController
                                //coverArtList[number]//R.drawable.tree
                            )
                        }
                    }
                ) // Ends items
            } // Ends LazyColumn

    } // Ends Scaffold

} // Ends QuestListScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun QuestCard(currentQuest: Quest, navController: NavController){

    var coverArt = R.drawable.dragon

    if (currentQuest.quest_theme == "Dungeon") {
        coverArt = R.drawable.dragon
    } else if (currentQuest.quest_theme == "Castle") {
        coverArt = R.drawable.grimes_art
    } else if (currentQuest.quest_theme == "Nature") {
        coverArt = R.drawable.tree
    }

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            //.fillMaxWidth(.5f)
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
        title = {Text(text = "Re-Quest", fontSize = 18.sp)},
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.Black,
    ) // Ends TopAppBar
} // Ends TopBar