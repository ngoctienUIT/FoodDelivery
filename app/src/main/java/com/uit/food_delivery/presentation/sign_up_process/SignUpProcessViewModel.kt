package com.uit.food_delivery.presentation.sign_up_process

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uit.food_delivery.core.extensions.isPhoneNumber
import com.uit.food_delivery.core.utils.EventResults
import com.uit.food_delivery.core.utils.EventStatus
import com.uit.food_delivery.data.response.UserResponse
import com.uit.food_delivery.network.api.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpProcessViewModel @Inject constructor(private val authService: AuthService) :
    ViewModel() {
    private val _register: MutableStateFlow<EventResults<UserResponse>> =
        MutableStateFlow(EventResults())
    val register: StateFlow<EventResults<UserResponse>> = _register

    var fullname: MutableState<String> = mutableStateOf("")
    var fullNameErrMsg: MutableState<String> = mutableStateOf("")

    var birthday: MutableState<String> = mutableStateOf("")
    var birthdayErrMsg: MutableState<String> = mutableStateOf("")

    var phoneNumber: MutableState<String> = mutableStateOf("")
    var phoneNumberErrMsg: MutableState<String> = mutableStateOf("")

    var isEnableButton: MutableState<Boolean> = mutableStateOf(false)

    suspend fun signup(username: String, email: String, password: String) {
        Log.d("test", "sign up")
        _register.value = EventResults(status = EventStatus.LOADING)
        viewModelScope.launch {
            try {
                val data = authService.signup(
                    mapOf(
                        "name" to fullname.value,
                        "password" to password,
                        "username" to username,
                        "email" to email,
                        "phoneNumber" to phoneNumber.value,
                        "isMale" to true,
                        "birthOfDate" to birthday.value
                    )
                )
                if (data.isSuccessful) {
                    _register.value =
                        EventResults(status = EventStatus.SUCCESS, data = data.body())
                    Log.d("data", data.body().toString())
                } else {
                    val errMsg = data.errorBody()?.string() ?: run { data.code().toString() }
                    _register.value =
                        EventResults(status = EventStatus.ERROR, error = errMsg)
                    Log.d("error data", errMsg)
                }
            } catch (e: Exception) {
                println(e.message.toString())
                _register.value = EventResults(status = EventStatus.ERROR, error = e.message)
                Log.d("error", e.message.toString())
            }
        }
    }

    fun validatePhoneNumber() {
        if (phoneNumber.value.isPhoneNumber()) {
            phoneNumberErrMsg.value = "Số điện thoại không đúng định dạng"
        } else {
            phoneNumberErrMsg.value = ""
        }
        enableButton()
    }

    fun validateFullName() {
        if (fullname.value.isEmpty()) {
            fullNameErrMsg.value = "Vui lòng nhập tên đầy đủ"
        } else {
            fullNameErrMsg.value = ""
        }
        enableButton()
    }

    private fun enableButton() {
        val check: Boolean =
            phoneNumberErrMsg.value.isEmpty() && fullNameErrMsg.value.isEmpty() && birthdayErrMsg.value.isEmpty()
                    && phoneNumber.value.isNotEmpty() && fullname.value.isNotEmpty() && birthday.value.isNotEmpty()
        if (isEnableButton.value != check) isEnableButton.value = check
    }
}