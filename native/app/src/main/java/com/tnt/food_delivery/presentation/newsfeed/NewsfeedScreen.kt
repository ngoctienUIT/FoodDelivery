package com.tnt.food_delivery.presentation.newsfeed

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme

@OptIn(ExperimentalMaterial3Api::class)
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