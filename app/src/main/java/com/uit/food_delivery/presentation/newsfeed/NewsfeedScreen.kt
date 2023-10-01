package com.uit.food_delivery.presentation.newsfeed

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.uit.food_delivery.ui.theme.FoodDeliveryTheme

@Composable
fun NewsfeedScreen(navController: NavController) {
    Scaffold {
        it
        Column {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderPreview() {
    FoodDeliveryTheme {
        NewsfeedScreen(rememberNavController())
    }
}