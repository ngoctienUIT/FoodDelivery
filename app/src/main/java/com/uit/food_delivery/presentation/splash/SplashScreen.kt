package com.uit.food_delivery.presentation.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.uit.food_delivery.R
import com.uit.food_delivery.core.utils.NavDestinations
import com.uit.food_delivery.ui.components.CustomScaffold
import com.uit.food_delivery.ui.components.LogoApp
import com.uit.food_delivery.ui.theme.DarkTheme
import com.uit.food_delivery.ui.theme.FoodDeliveryTheme
import com.uit.food_delivery.ui.theme.LocalTheme

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel(),
    onChangeTheme: (theme: Boolean) -> Unit
) {
    LaunchedEffect(viewModel.token, viewModel.isDark) {
        if (viewModel.isDark != null) {
            LocalTheme.provides(DarkTheme(viewModel.isDark ?: false))
            onChangeTheme(viewModel.isDark ?: false)
            if (viewModel.token != null) {
                navController.popBackStack()
                navController.navigate(if (viewModel.token.isNullOrEmpty()) NavDestinations.SIGNIN_SCREEN else NavDestinations.MAIN_SCREEN) {
                    popUpTo(NavDestinations.SPLASH_SCREEN) { inclusive = true }
                }
            }
        }
    }

    CustomScaffold(
        navController = navController,
        bgImage = R.drawable.background_light,
        isTabBar = false,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            LogoApp()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    FoodDeliveryTheme {
        SplashScreen(rememberNavController()) {}
    }
}