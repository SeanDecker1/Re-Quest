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
        }

        composable(route = Screens.QuestListScreen.route) {
            QuestListScreen(questViewModel = questViewModel, navController = navController)
        }

        composable(
            route = Screens.QuestScreen.route+"/{questName}",
            arguments = listOf(
                navArgument("questName"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )

        ) { entry ->
            QuestDetailsScreen(questName = entry.arguments?.getString("questName"))
        }

    } // Ends NavHost

} // Ends NavRoutes