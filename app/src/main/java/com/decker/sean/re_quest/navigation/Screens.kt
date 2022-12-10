package com.decker.sean.re_quest.navigation

sealed class Screens(val route: String) {
    object Login: Screens("login_screen")
    object QuestList: Screens("quest_list_screen")
    object QuestDetails: Screens("quest_details_screen")
} // Ends Screens sealed class