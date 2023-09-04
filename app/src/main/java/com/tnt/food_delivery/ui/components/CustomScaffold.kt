package com.tnt.food_delivery.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold(
    navController: NavController,
    bgImage: Int,
    title: String? = null,
    navigationIcon: @Composable (() -> Unit)? = null,
    isTabBar: Boolean = true,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            if (isTabBar)
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent),
                    title = {
                        if (title != null)
                            Text(
                                text = title,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF09051C)
                            )
                    },
                    navigationIcon = {
                        if (navigationIcon == null)
                            BackButton {
                                navController.popBackStack()
                            }
                    }
                )
        },
        containerColor = Color.Transparent
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = bgImage),
                contentDescription = "tnt"
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
            ) {
                content()
            }
        }
    }
}