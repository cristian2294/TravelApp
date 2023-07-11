package com.example.travelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelapp.commons.Routes
import com.example.travelapp.login.presentation.ui.screens.HomeScreen
import com.example.travelapp.login.presentation.ui.screens.LoginScreen
import com.example.travelapp.login.presentation.ui.screens.SignUpScreen
import com.example.travelapp.login.presentation.ui.screens.StartScreen
import com.example.travelapp.login.presentation.ui.viewmodel.SignUpViewModel
import com.example.travelapp.ui.theme.TravelAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val signUpViewModel: SignUpViewModel by viewModels()
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
                    composable(route = Routes.SignUpScreen.route) {
                        SignUpScreen(
                            navController,
                            signUpViewModel,
                        )
                    }
                    composable(route = Routes.LoginScreen.route) { LoginScreen(navController) }
                    composable(route = Routes.HomeScreen.route) { HomeScreen() }
                }
            }
        }
    }
}
