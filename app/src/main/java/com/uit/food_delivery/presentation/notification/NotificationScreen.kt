package com.uit.food_delivery.presentation.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.uit.food_delivery.R
import com.uit.food_delivery.ui.components.CustomScaffold
import com.uit.food_delivery.ui.components.shadow
import com.uit.food_delivery.ui.theme.FoodDeliveryTheme

@Composable
fun NotificationScreen(navController: NavController) {
    CustomScaffold(
        navController = navController,
        title = "Notification",
        bgImage = R.drawable.triangle_background
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .fillMaxSize(),
        ) {
            items(10) { index ->
                Spacer(modifier = Modifier.height(20.dp))
                if (index % 3 == 0)
                    ItemNotification(
                        R.drawable.success_notification_icon,
                        title = "Your order has been taken by the driver",
                        time = "Recently"
                    )
                if (index % 3 == 1)
                    ItemNotification(
                        R.drawable.money_notification_icon,
                        title = "Topup for \$100 was successful",
                        time = "10.00 Am"
                    )
                if (index % 3 == 2)
                    ItemNotification(
                        R.drawable.cancel_notification_icon,
                        title = "Your order has been canceled",
                        time = "22 Juny 2021"
                    )
            }
        }
    }
}

@Composable
fun ItemNotification(id: Int, title: String, time: String) {
    Card(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .shadow(
                color = Color(0xFF5A6CEA).copy(alpha = 0.07f),
                spread = 25.dp,
                blurRadius = 50.dp,
            ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(22)
    ) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp),
                painter = painterResource(id = id),
                contentDescription = "tnt",
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = time,
                    color = Color(0xFF3B3B3B).copy(alpha = 0.3f),
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationScreenPreview() {
    FoodDeliveryTheme {
        NotificationScreen(rememberNavController())
    }
}