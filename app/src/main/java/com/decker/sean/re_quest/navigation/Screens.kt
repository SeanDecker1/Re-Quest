package com.decker.sean.re_quest.navigation

sealed class Screens(val route: String) {
    object LoginScreen: Screens("login_screen")
    object QuestListScreen: Screens("quest_list_screen")
    object QuestScreen: Screens("quest_details_screen")

    fun withArgs(vararg args: Int): String{
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    } // Ends withArgs

    fun withStringArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    } // Ends withStringArgs

} // Ends Screens sealed class