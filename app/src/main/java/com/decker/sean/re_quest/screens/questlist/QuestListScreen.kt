package com.decker.sean.re_quest.screens.questlist

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.decker.sean.re_quest.navigation.Screens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun QuestListScreen(navController: NavController) {

    //dummy View Model for now
    val questListViewModel = arrayListOf<String>("My First Quest", "Dungeon Quest 2", "Castle Raid")


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Quest List Screen",
                fontSize = MaterialTheme.typography.h3.fontSize,
                fontWeight = FontWeight.Bold
            ) // Ends Text
        }// Ends Row

        Spacer(modifier = Modifier.padding(36.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 75.dp),
        ) {
            items(
                items = questListViewModel,
                itemContent = {
                    Button(
                        shape = MaterialTheme.shapes.medium,
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                        modifier = Modifier.padding(5.dp),
                        onClick = {
                            navController.navigate(Screens.QuestScreen.withArgs(it)) {
                                popUpTo(Screens.QuestScreen.route){
                                    inclusive = true
                                }
                            }
                        }
                    ){
                        Text(
                            text = "To Quest: $it",
                            modifier = Modifier.padding(5.dp),
                            style = MaterialTheme.typography.button.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            ) // Ends style
                        ) // Ends Text
                    } // Ends Button
                }
            )

        } // Ends Row

    } // Ends Column

} // Ends QuestListScreen