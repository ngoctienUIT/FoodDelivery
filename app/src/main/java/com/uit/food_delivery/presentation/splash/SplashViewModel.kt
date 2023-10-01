package com.uit.food_delivery.presentation.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uit.food_delivery.data.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val dataStoreManager: DataStoreManager) :
    ViewModel() {
    var isDark by mutableStateOf<Boolean?>(null)
    var token by mutableStateOf<String?>(null)

    init {
        getTheme()
        getToken()
    }

    private fun getTheme() {
        viewModelScope.launch {
            isDark = dataStoreManager.getBoolean("isDark").first() ?: false
        }
    }

    private fun getToken() {
        viewModelScope.launch {
            token = dataStoreManager.getString("token").first()
        }
    }
}