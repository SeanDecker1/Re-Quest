package com.decker.sean.re_quest.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.decker.sean.re_quest.navigation.Screens

@Composable
fun LoginScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Re-Quest",
                fontSize = MaterialTheme.typography.h3.fontSize,
                fontWeight = FontWeight.Bold
            ) // Ends Text
        }// Ends Row

        // Game Master button row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {

            Button(
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                modifier = Modifier.padding(5.dp),
                onClick = {
                    navController.navigate(Screens.QuestListScreen.withStringArgs("gamemaster")) {
                        popUpTo(Screens.QuestListScreen.route){
                            inclusive = true
                        }
                    } // Ends nav
                    //navController.navigate(Screens.QuestListScreen.route)
                }
            ){
                Text(
                    text = "Game Master",
                    modifier = Modifier.padding(5.dp),
                    style = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ) // Ends style
                ) // Ends Text
            } // Ends Button

        } // Ends Row

        // Player button row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {

            Button(
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                modifier = Modifier.padding(5.dp),
                onClick = {
                    navController.navigate(Screens.QuestListScreen.withStringArgs("player")) {
                        popUpTo(Screens.QuestListScreen.route){
                            inclusive = true
                        }
                    } // Ends nav
                    //navController.navigate(Screens.QuestListScreen.route)
                }
            ){
                Text(
                    text = "Player",
                    modifier = Modifier.padding(5.dp),
                    style = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ) // Ends style
                ) // Ends Text
            } // Ends Button

        } // Ends Row

    } // Ends Column

} // Ends LoginScreen