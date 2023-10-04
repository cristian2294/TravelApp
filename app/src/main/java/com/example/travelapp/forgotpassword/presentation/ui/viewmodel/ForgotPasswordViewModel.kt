package com.example.travelapp.forgotpassword.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelapp.forgotpassword.domain.usecase.ForgotPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
) : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _isBtnContinueEnabled = MutableLiveData<Boolean>()
    val isBtnContinueEnabled: LiveData<Boolean> get() = _isBtnContinueEnabled

    fun onResetPasswordClicked(
        showToastPasswordResetSuccess: () -> Unit,
        showToastPasswordResetError: () -> Unit,
    ) {
        viewModelScope.launch {
            val isEmailValid = forgotPasswordUseCase.isEmailValid(email.value ?: "")
            if (isEmailValid) {
                forgotPasswordUseCase.resetPassword(
                    email.value ?: "",
                    showToastPasswordResetSuccess,
                    showToastPasswordResetError,
                )
            } else {
                showToastPasswordResetError()
            }
        }
    }

    fun onEmailChanged(email: String) {
        _email.value = email
        _isBtnContinueEnabled.value = forgotPasswordUseCase.isEmailValid(email)
    }
}
