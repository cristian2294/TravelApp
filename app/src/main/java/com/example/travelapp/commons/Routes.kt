package com.example.travelapp.commons

sealed class Routes(val route: String) {
    object StartScreen : Routes("start_screen")
    object LoginScreen : Routes("login_screen")
    object SignUpScreen : Routes("sign_up_screen")
    object HomeScreen : Routes("home_screen")
    object ForgotPasswordScreen : Routes("forgot_password_screen")
}
