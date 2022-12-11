package com.decker.sean.re_quest.screens.questlist

import android.annotation.SuppressLint

import androidx.compose.foundation.BorderStroke

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
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.outlined.HistoryEdu
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.decker.sean.re_quest.R

import com.decker.sean.re_quest.navigation.Screens
import com.decker.sean.re_quest.ui.theme.ReQuestTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun QuestListScreen(navController: NavController) {

    //dummy View Model for now
    val questListViewModel = arrayListOf(listOf("My First Quest", R.drawable.tree), listOf("Dungeon Quest 2", R.drawable.dragon), listOf("Castle Raid", R.drawable.grimes_art))




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

            Button(
                onClick = { /* ... */ },
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
                    contentDescription = "Add New Quest",
                    modifier = Modifier
                        .padding(0.dp)
                        .size(24.dp)
                )
            }
        }// Ends Row

        Spacer(modifier = Modifier.padding(36.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 75.dp, start = 8.dp),
        ) {
            items(
                items = questListViewModel,
                itemContent = {
                    QuestCard(currentQuest = it[0].toString(), navController = navController, coverArt = it[1] as Int)
                }
            )

        } // Ends Row

    } // Ends Scaffold

} // Ends QuestListScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun QuestCard(currentQuest: String, navController: NavController, coverArt: Int){
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth(.5f)
            .padding(bottom = 20.dp),
        onClick = {
            navController.navigate(Screens.QuestScreen.withArgs(currentQuest)) {
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
                )
            }

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
                )

                Text(
                    text = currentQuest,
                    modifier = Modifier.padding(5.dp),
                    style = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ) // Ends style
                ) // Ends Text
            }
        }//Column



    } // Ends Card
}

@Composable
fun TopBar(){
    TopAppBar(
        title = {Text(text = "Re-Quest", fontSize = 18.sp)},
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.Black,
    )
}