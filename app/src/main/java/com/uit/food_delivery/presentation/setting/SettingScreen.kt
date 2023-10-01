package com.uit.food_delivery.presentation.setting

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.uit.food_delivery.ui.components.BackButton
import com.uit.food_delivery.ui.theme.DarkTheme
import com.uit.food_delivery.ui.theme.FoodDeliveryTheme
import com.uit.food_delivery.ui.theme.LocalTheme
import kotlinx.coroutines.launch

@Composable
fun SettingScreen(
    navController: NavController,
    viewModel: SettingViewModel = hiltViewModel(),
    onChange: (theme: Boolean) -> Unit,
) {
    val isDark = LocalTheme.current.isDark
    var darkTheme by remember { mutableStateOf(isDark) }
    val scope = rememberCoroutineScope()

    Scaffold(containerColor = MaterialTheme.colorScheme.background) {
        it
        Column {
            BackButton {
                navController.popBackStack()
            }
            Button(
                onClick = {
                    darkTheme = !darkTheme
//                    LocalTheme.provides(DarkTheme(darkTheme))
//                    LocalTheme.providesDefault(DarkTheme(darkTheme))
                    LocalTheme = compositionLocalOf { DarkTheme(isDark = darkTheme) }
                    Log.d("theme", darkTheme.toString())

                    scope.launch {
                        viewModel.changeTheme(darkTheme)
                        onChange(darkTheme)
                    }
                }
            ) {
                Text(text = "Đổi theme")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    FoodDeliveryTheme { SettingScreen(rememberNavController()) {} }
}