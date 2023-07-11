package com.example.travelapp.login.domain.usecase

import android.util.Patterns
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class SignUpUseCase @Inject constructor() {

    fun registerUserWithEmailAndPassword(
        email: String,
        password: String,
        navigateToHome: () -> Unit,
        showSignUpError: () -> Unit,
    ) {
        Firebase.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                navigateToHome()
            } else {
                showSignUpError()
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

const val MINIMUM_PASSWORD_CHARACTERS = 6
