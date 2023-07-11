package com.example.travelapp.login.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelapp.login.domain.usecase.SignUpUseCase
import com.example.travelapp.login.presentation.states.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _isLoginEnabled = MutableLiveData<Boolean>()
    val isLoginEnabled: LiveData<Boolean> get() = _isLoginEnabled

    private val _stateSignUpEmail = MutableLiveData<SignUpState>()
    val stateSignUpEmail: LiveData<SignUpState> get() = _stateSignUpEmail

    fun onSignUpClickListener(
        navigateToHome: () -> Unit,
        showSignUpError: () -> Unit,
    ) {
        viewModelScope.launch {
            _stateSignUpEmail.postValue(SignUpState.Loading)
            withContext(Dispatchers.IO) {
                val result = signUpUseCase.isLoginWithEmailAndPasswordEnabled(
                    email.value ?: "",
                    password.value ?: "",
                )
                if (result) {
                    signUpUseCase.registerUserWithEmailAndPassword(
                        email.value ?: "",
                        password.value ?: "",
                        navigateToHome,
                        showSignUpError,
                    )
                    _stateSignUpEmail.postValue(SignUpState.Success)
                } else {
                    _stateSignUpEmail.postValue(SignUpState.Error("Por favor, complete todos los campos."))
                }
            }
        }
    }

    fun onSignUpChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isLoginEnabled.value = signUpUseCase.isLoginWithEmailAndPasswordEnabled(email, password)
    }
}
