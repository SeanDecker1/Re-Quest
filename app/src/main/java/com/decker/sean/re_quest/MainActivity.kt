package com.decker.sean.re_quest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.decker.sean.re_quest.models.QuestViewModel
import com.decker.sean.re_quest.navigation.NavRoutes
import com.decker.sean.re_quest.ui.theme.ReQuestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val questViewModel = ViewModelProvider(this).get(QuestViewModel::class.java)

        setContent {
            ReQuestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberNavController()
                    NavRoutes(questViewModel = questViewModel, navController = navController)

                } // Ends Surface
            } // Ends Theme
        } // Ends setContent
    } // Ends onCreate
} // Ends MainActivity


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ReQuestTheme {

    }
}