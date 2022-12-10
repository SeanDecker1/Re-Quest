package com.decker.sean.re_quest.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.decker.sean.re_quest.navigation.Screens

@Composable
fun LoginScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp),
            value = value,
            onValueChange = { onNewValue(it) },
            placeholder = { Text(stringResource(AppText.email)) },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") }
        )

    } // Ends Column

//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(10.dp),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            Text(
//                text = "Login Screen",
//                fontSize = MaterialTheme.typography.h3.fontSize,
//                fontWeight = FontWeight.Bold
//            ) // Ends Text
//        }// Ends Row
//
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
//                    navController.navigate(Screens.QuestList.route)
//                }
//            ){
//                Text(
//                    text = "To Quest List Screen",
//                    modifier = Modifier.padding(5.dp),
//                    style = MaterialTheme.typography.button.copy(
//                        fontWeight = FontWeight.Bold,
//                        color = Color.White
//                    ) // Ends style
//                ) // Ends Text
//            } // Ends Button
//
//        } // Ends Row
//
//    } // Ends Column

} // Ends LoginScreen