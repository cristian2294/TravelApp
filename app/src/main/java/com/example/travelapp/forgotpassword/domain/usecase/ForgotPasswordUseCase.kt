package com.example.travelapp.forgotpassword.domain.usecase

import android.util.Patterns
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor() {
    fun isEmailValid(email: String): Boolean = validateEmail(email)

    private fun validateEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun resetPassword(
        email: String,
        showToastPasswordResetSuccess: () -> Unit,
        showToastPasswordResetError: () -> Unit,
    ) {
        Firebase.auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { showToastPasswordResetSuccess() }
            .addOnFailureListener { showToastPasswordResetError() }
    }
}
