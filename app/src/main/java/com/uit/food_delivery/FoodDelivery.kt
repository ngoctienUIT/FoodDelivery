package com.uit.food_delivery

import android.os.Build
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.uit.food_delivery.core.nav_type.ProductNavType
import com.uit.food_delivery.core.nav_type.UserNavType
import com.uit.food_delivery.core.utils.NavDestinations
import com.uit.food_delivery.data.response.ProductResponse
import com.uit.food_delivery.data.response.UserResponse
import com.uit.food_delivery.presentation.filter.FilterScreen
import com.uit.food_delivery.presentation.main.MainScreen
import com.uit.food_delivery.presentation.product_detail.ProductDetailScreen
import com.uit.food_delivery.presentation.restaurant_detail.RestaurantDetailScreen
import com.uit.food_delivery.presentation.setting.SettingScreen
import com.uit.food_delivery.presentation.sign_in.SignInScreen
import com.uit.food_delivery.presentation.sign_up.SignUpScreen
import com.uit.food_delivery.presentation.sign_up_process.SignUpProcessScreen
import com.uit.food_delivery.presentation.sign_up_success.SignUpSuccessScreen
import com.uit.food_delivery.presentation.splash.SplashScreen
import com.uit.food_delivery.presentation.upload_photo.UploadPhotoScreen
import com.uit.food_delivery.ui.theme.DarkTheme
import com.uit.food_delivery.ui.theme.FoodDeliveryTheme
import com.uit.food_delivery.ui.theme.LocalTheme

@Composable
@OptIn(ExperimentalTextApi::class, ExperimentalMaterial3Api::class)
fun FoodDelivery() {
    val darkTheme = LocalTheme.current.isDark

    LaunchedEffect(LocalTheme.current.isDark) {
        Log.d("theme", darkTheme.toString())
    }

    CompositionLocalProvider(LocalTheme provides DarkTheme(isSystemInDarkTheme())) {
        FoodDeliveryTheme(darkTheme = LocalTheme.current.isDark) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = NavDestinations.SPLASH_SCREEN,
                ) {
                    composable(NavDestinations.SPLASH_SCREEN) {
                        SplashScreen(navController) { /*theme -> darkTheme = theme*/ }
                    }
                    composable(NavDestinations.SIGNIN_SCREEN) {
                        SignInScreen(navController)
                    }
                    composable(NavDestinations.SIGNUP_SCREEN) {
                        SignUpScreen(navController)
                    }
                    composable(
                        "${NavDestinations.SIGNUP_PROCESS_SCREEN}/{username}/{email}/{password}",
                        arguments = listOf(
                            navArgument("username") { type = NavType.StringType },
                            navArgument("email") { type = NavType.StringType },
                            navArgument("password") { type = NavType.StringType },
                        )
                    ) { backStackEntry ->
                        val username = backStackEntry.arguments?.getString("username")
                        val email = backStackEntry.arguments?.getString("email")
                        val password = backStackEntry.arguments?.getString("password")
                        if (username != null && email != null && password != null) {
                            SignUpProcessScreen(
                                navController,
                                username = username,
                                email = email,
                                password = password
                            )
                        }
                    }
                    composable(NavDestinations.UPLOAD_PHOTO_SCREEN) {
                        UploadPhotoScreen(navController)
                    }
                    composable(NavDestinations.SIGNUP_SUCCESS_SCREEN) {
                        SignUpSuccessScreen(navController)
                    }
                    composable(NavDestinations.SETTING_SCREEN) {
                        SettingScreen(navController) { theme ->
//                            darkTheme = theme
                        }
                    }
                    composable(NavDestinations.MAIN_SCREEN) {
                        MainScreen(navController)
                    }
                    composable(NavDestinations.FILTER_SCREEN) {
                        FilterScreen(navController)
                    }
                    composable(
                        "${NavDestinations.RESTAURANT_DETAIL_SCREEN}/{restaurant}",
                        arguments = listOf(
                            navArgument("restaurant") { type = UserNavType() },
                        )
                    ) { backStackEntry ->
                        val restaurant =
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                backStackEntry.arguments?.getParcelable(
                                    "restaurant",
                                    UserResponse::class.java
                                )
                            } else {
                                @Suppress("DEPRECATION") backStackEntry.arguments?.getParcelable(
                                    "restaurant"
                                )
                            }
                        if (restaurant != null) {
                            RestaurantDetailScreen(navController, restaurant)
                        }
                    }
                    composable(
                        "${NavDestinations.PRODUCT_DETAIL_SCREEN}/{product}",
                        arguments = listOf(
                            navArgument("product") { type = ProductNavType() },
                        )
                    ) { backStackEntry ->
                        val product = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            backStackEntry.arguments?.getParcelable(
                                "product",
                                ProductResponse::class.java
                            )
                        } else {
                            @Suppress("DEPRECATION") backStackEntry.arguments?.getParcelable(
                                "product"
                            )
                        }
                        if (product != null) {
                            ProductDetailScreen(navController, product)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodDeliveryTheme { FoodDelivery() }
}