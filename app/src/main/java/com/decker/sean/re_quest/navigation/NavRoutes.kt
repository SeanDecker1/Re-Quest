package com.decker.sean.re_quest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.decker.sean.re_quest.screens.login.LoginScreen
import com.decker.sean.re_quest.screens.questdetails.QuestDetailsScreen
import com.decker.sean.re_quest.screens.questlist.QuestListScreen

@Composable
fun NavRoutes(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screens.Login.route
    ) {

        composable(route = Screens.Login.route) {
            LoginScreen(navController)
        }

        composable(route = Screens.QuestList.route) {
            QuestListScreen(navController)
        }

        composable(route = Screens.QuestDetails.route) {
            QuestDetailsScreen()
        }

    } // Ends NavHost

} // Ends NavRoutes