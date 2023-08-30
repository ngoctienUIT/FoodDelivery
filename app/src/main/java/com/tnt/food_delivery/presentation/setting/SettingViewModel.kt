package com.tnt.food_delivery.presentation.setting

import androidx.lifecycle.ViewModel
import com.tnt.food_delivery.data.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val dataStoreManager: DataStoreManager) :
    ViewModel() {
    suspend fun changeTheme(theme: Boolean) {
        dataStoreManager.setBoolean("isDark", theme)
    }
}