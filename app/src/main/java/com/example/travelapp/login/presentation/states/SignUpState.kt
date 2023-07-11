package com.example.travelapp.login.presentation.states

sealed class SignUpState {
    object Loading : SignUpState()
    object Success : SignUpState()
    data class Error(val message: String) : SignUpState()
}
