package com.example.travelapp.login.domain.usecase

import android.util.Patterns
import com.example.travelapp.signup.domain.usecase.MINIMUM_PASSWORD_CHARACTERS
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class LoginUseCase @Inject constructor() {

    fun loginWithEmailAndPassword(
        email: String,
        password: String,
        navigateToHome: () -> Unit,
        showLoginError: () -> Unit,
    ) {
        Firebase.auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                navigateToHome()
            } else {
                showLoginError()
            }
        }
    }

    fun isLoginWithEmailAndPasswordEnabled(email: String, password: String): Boolean =
        validateEmail(email) && validatePassword(password)

    private fun validateEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun validatePassword(password: String): Boolean =
        password.length > MINIMUM_PASSWORD_CHARACTERS
}
