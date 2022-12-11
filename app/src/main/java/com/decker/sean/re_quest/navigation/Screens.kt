package com.decker.sean.re_quest.navigation

sealed class Screens(val route: String) {
    object LoginScreen: Screens("login_screen")
    object QuestListScreen: Screens("quest_list_screen")
    object QuestScreen: Screens("quest_details_screen")

    // Switched with uncommented version
    //fun withArgs(vararg args: String): String{
    fun withArgs(vararg args: Int): String{
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }
} // Ends Screens sealed class