package com.example.travelapp.signup.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelapp.signup.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
) : ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _isLoginEnabled = MutableLiveData<Boolean>()
    val isLoginEnabled: LiveData<Boolean> get() = _isLoginEnabled

    private val _isLoading = MutableLiveData(false)

    fun onSignUpClickListener(
        navigateToHome: () -> Unit,
        showSignUpError: () -> Unit,
    ) {
        if (_isLoading.value == false) {
            _isLoading.value = true

            viewModelScope.launch {
                val result = signUpUseCase.isSignUpWithEmailAndPasswordEnabled(
                    email.value ?: "",
                    password.value ?: "",
                )
                if (result) {
                    signUpUseCase.registerUserWithEmailAndPassword(
                        name.value ?: "",
                        email.value ?: "",
                        password.value ?: "",
                        navigateToHome,
                        showSignUpError,
                    )
                } else {
                    showSignUpError()
                }
            }
            _isLoading.value = false
        }
    }

    fun onSignUpChanged(
        name: String,
        email: String,
        password: String,
    ) {
        _name.value = name
        _email.value = email
        _password.value = password
        _isLoginEnabled.value = signUpUseCase.isSignUpWithEmailAndPasswordEnabled(email, password)
    }

    fun isUserLogged(
        navigateToHome: () -> Unit,
    ) {
        if (signUpUseCase.isUserLogged()) {
            navigateToHome()
        }
    }
}
