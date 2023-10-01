package com.uit.food_delivery.presentation.sign_in

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uit.food_delivery.core.utils.EventResults
import com.uit.food_delivery.core.utils.EventStatus
import com.uit.food_delivery.data.response.AuthenticationResponse
import com.uit.food_delivery.network.api.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val authService: AuthService) : ViewModel() {
    private val _authentication: MutableStateFlow<EventResults<AuthenticationResponse>> =
        MutableStateFlow(EventResults())
    val authentication: StateFlow<EventResults<AuthenticationResponse>> = _authentication

    var username: MutableState<String> = mutableStateOf("")
    var usernameErrMsg: MutableState<String> = mutableStateOf("")

    var password: MutableState<String> = mutableStateOf("")
    var passwordErrMsg: MutableState<String> = mutableStateOf("")

    var isEnableButton: MutableState<Boolean> = mutableStateOf(false)

    suspend fun login() {
        Log.d("test", "login")
        _authentication.value = EventResults(status = EventStatus.LOADING)
        viewModelScope.launch {
            try {
                val data = authService.login(
                    mapOf(
                        "username" to username.value,
                        "password" to password.value
                    )
                )
                if (data.isSuccessful) {
                    _authentication.value =
                        EventResults(status = EventStatus.SUCCESS, data = data.body())
                    Log.d("data", data.body().toString())
                } else {
                    val errMsg = data.errorBody()?.string() ?: run { data.code().toString() }
                    _authentication.value =
                        EventResults(status = EventStatus.ERROR, error = errMsg)
                    Log.d("error data", errMsg)
                }

            } catch (e: Exception) {
                println(e.message.toString())
                _authentication.value = EventResults(status = EventStatus.ERROR, error = e.message)
                Log.d("error", e.message.toString())
            }
        }
    }

    fun validateUsername() {
        if (username.value.isEmpty()) {
            usernameErrMsg.value = "Vui lòng nhập vào username"
        } else {
            usernameErrMsg.value = ""
        }
        enableButton()
    }

    fun validatePassword() {
        android.util.Patterns.EMAIL_ADDRESS
        if (password.value.isEmpty()) {
            passwordErrMsg.value = "Vui lòng nhập vào mật khẩu"
        } else {
            passwordErrMsg.value = ""
        }
        enableButton()
    }

    private fun enableButton() {
        val check: Boolean =
            passwordErrMsg.value.isEmpty() && usernameErrMsg.value.isEmpty()
                    && password.value.isNotEmpty() && username.value.isNotEmpty()
        if (isEnableButton.value != check) isEnableButton.value = check
    }
}
