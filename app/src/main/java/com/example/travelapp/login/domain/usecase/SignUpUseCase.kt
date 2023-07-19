package com.example.travelapp.login.domain.usecase

import android.util.Patterns
import com.example.travelapp.login.presentation.model.Customer
import com.example.travelapp.utils.Constants.Companion.CUSTOMER_REFERENCE
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class SignUpUseCase @Inject constructor() {

    private val auth = Firebase.auth
    private val db = FirebaseDatabase.getInstance().reference
    fun registerUserWithEmailAndPassword(
        name: String,
        email: String,
        password: String,
        navigateToHome: () -> Unit,
        showSignUpError: () -> Unit,
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                registerCustomerInRemoteDb(name, email, password, auth, db)
                navigateToHome()
            } else {
                showSignUpError()
            }
        }
    }

    private fun registerCustomerInRemoteDb(
        name: String,
        email: String,
        password: String,
        auth: FirebaseAuth,
        db: DatabaseReference,
    ) {
        val customerId = auth.currentUser?.uid
        val customer = Customer(
            id = customerId.toString(),
            name = name,
            email = email,
            password = password,
        ).toMap()

        db.child(CUSTOMER_REFERENCE).child(customerId!!).setValue(customer)
    }

    fun isSignUpWithEmailAndPasswordEnabled(email: String, password: String): Boolean =
        validateEmail(email) && validatePassword(password)

    private fun validateEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun validatePassword(password: String): Boolean =
        password.length > MINIMUM_PASSWORD_CHARACTERS

    fun isUserLogged(): Boolean = !auth.currentUser?.email.isNullOrEmpty()
}

const val MINIMUM_PASSWORD_CHARACTERS = 6
