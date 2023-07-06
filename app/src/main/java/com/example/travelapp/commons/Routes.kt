package com.example.travelapp.commons

sealed class Routes(val route: String) {
    object StartScreen : Routes("start_screen")
    object LoginScreen : Routes("login_screen")
    object HomeScreen : Routes("home_screen")
}
