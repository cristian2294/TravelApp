package com.example.travelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelapp.commons.Routes
import com.example.travelapp.login.ui.screens.SignUpScreen
import com.example.travelapp.login.ui.screens.StartScreen
import com.example.travelapp.ui.theme.TravelAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "start_screen",
                ) {
                    composable(route = Routes.StartScreen.route) { StartScreen(navController) }
                    composable(route = Routes.SignUpScreen.route) { SignUpScreen(navController) }
                }
            }
        }
    }
}
