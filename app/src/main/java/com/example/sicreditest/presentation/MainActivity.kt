package com.example.sicreditest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sicreditest.presentation.event_checkIn.EventCheckinScreen
import com.example.sicreditest.presentation.event_detail.EventDetailScreen
import com.example.sicreditest.presentation.event_list.EventListScreen
import com.example.sicreditest.presentation.ui.theme.SicrediTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SicrediTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.EventListScreen.route
                        ){
                            composable(
                                route = Screen.EventListScreen.route
                            ){
                               EventListScreen(navController)
                            }
                            composable(
                                route = Screen.EventDetailScreen.route + "/{eventId}"
                            ){
                                EventDetailScreen(navController)
                            }
                            composable(
                                route = Screen.EventCheckInScreen.route + "/{eventId}"
                            ){
                                EventCheckinScreen(navController)
                            }
                        }
                }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    SicrediTestTheme {
//        Greeting("Android")
//    }
//}