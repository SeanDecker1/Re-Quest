package com.decker.sean.re_quest.screens.questdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun QuestDetailsScreen(questName: String?) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Quest Details Screen for: $questName",
                fontSize = MaterialTheme.typography.h3.fontSize,
                fontWeight = FontWeight.Bold
            ) // Ends Text
        }// Ends Row

        // Change to nav back to list
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(10.dp),
//            horizontalArrangement = Arrangement.Center
//        ) {
//
//            Button(
//                shape = MaterialTheme.shapes.medium,
//                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
//                modifier = Modifier.padding(5.dp),
//                onClick = {
//                    /*TODO: Navigate to list screen */
//                }
//            ){
//                Text(
//                    text = "List Screen",
//                    modifier = Modifier.padding(5.dp),
//                    style = MaterialTheme.typography.button.copy(
//                        fontWeight = FontWeight.Bold,
//                        color = Color.White
//                    ) // Ends style
//                ) // Ends Text
//            } // Ends Button
//
//        } // Ends Row

    } // Ends Column

} // Ends QuestDetailsScreen