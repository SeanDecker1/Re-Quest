package com.decker.sean.re_quest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.decker.sean.re_quest.models.QuestViewModel
import com.decker.sean.re_quest.screens.login.LoginScreen
import com.decker.sean.re_quest.screens.questdetails.QuestDetailsScreen
import com.decker.sean.re_quest.screens.questlist.QuestListScreen

@Composable
fun NavRoutes(questViewModel: QuestViewModel, navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screens.LoginScreen.route
    ) {

        composable(route = Screens.LoginScreen.route) {
            LoginScreen(navController)
        } // Ends LoginScreen nav

        composable(
            route = Screens.QuestListScreen.route+"/{userType}",
            arguments = listOf(
                navArgument("userType") {
                    type = NavType.StringType
                    defaultValue = "player"
                    nullable = false
                } // Ends navArgument
            ) // Ends arguments
        ) { entry ->
            QuestListScreen(questViewModel = questViewModel, navController = navController, userType = entry.arguments?.getString("userType"))
        } // Ends QuestList nav

        composable(
            route = Screens.QuestScreen.route+"/{questId}",
            arguments = listOf(
                navArgument("questId"){
                    type = NavType.StringType
                    defaultValue = "0"
                    nullable = false
                } // Ends navArgument
            ) // Ends arguments
        ) { entry ->
            QuestDetailsScreen(questViewModel = questViewModel, navController = navController, questId = entry.arguments?.getString("questId"))
        } // Ends QuestDetailsScreen nav

    } // Ends NavHost

} // Ends NavRoutes