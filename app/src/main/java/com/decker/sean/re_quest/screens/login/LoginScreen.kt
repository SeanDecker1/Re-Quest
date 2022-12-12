package com.decker.sean.re_quest.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.decker.sean.re_quest.R
import com.decker.sean.re_quest.navigation.Screens

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginScreen(navController: NavController) {

    Image(
        painter = painterResource(id = R.drawable.home_screen_castle),
        contentDescription = "Cover",
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.Crop
    ) // Ends Image

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(top = 16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = 0.dp,
            backgroundColor = MaterialTheme.colors.primary.copy(alpha = .8f)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 26.dp, end = 26.dp, top = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Re-Quest",
                        fontSize = MaterialTheme.typography.h3.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ) // Ends Text
                }// Ends Row

                Column(
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxWidth(.7f),
                ){
                    Row(
                        modifier = Modifier
                            .padding(0.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Button(
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .fillMaxWidth(),
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
                            .padding(bottom = 20.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Button(
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .fillMaxWidth(),
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
                }
                // Game Master button row


            } // Ends Column

        }
    }


} // Ends LoginScreen