package com.example.travelapp.login.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelapp.login.domain.usecase.LoginUseCase
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    fun onLoginClickListener(
        navigateToHome: () -> Unit,
        showLoginError: () -> Unit,
    ) {
        viewModelScope.launch {
            val result = loginUseCase.isLoginWithEmailAndPasswordEnabled(
                email.value ?: "",
                password.value ?: "",
            )
            if (result) {
                loginUseCase.loginWithEmailAndPassword(
                    email.value ?: "",
                    password.value ?: "",
                    navigateToHome,
                    showLoginError,
                )
            } else {
                showLoginError()
            }
        }
    }

    fun onLoginWithEmailAndPasswordChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
    }

    fun signInWithGoogleCredential(
        credential: AuthCredential,
        navigateToHome: () -> Unit,
        showLoginError: () -> Unit,
    ) {
        viewModelScope.launch {
            loginUseCase.signInWithGoogleCredential(credential, navigateToHome, showLoginError)
        }
    }
}
