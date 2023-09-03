package com.tnt.food_delivery.presentation.upload_photo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tnt.food_delivery.R
import com.tnt.food_delivery.ui.components.GradientButton
import com.tnt.food_delivery.ui.components.CustomScaffold
import com.tnt.food_delivery.ui.components.shadow
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme

@Composable
fun UploadPhotoScreen(navController: NavController) {
    CustomScaffold(
        navController = navController,
        bgImage = R.drawable.triangle_background
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .fillMaxSize(),
        ) {
            Text(
                modifier = Modifier.padding(end = 60.dp),
                text = "Upload Your Photo Profile",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier.padding(end = 90.dp),
                text = "This data will be displayed in your account profile for security",
                fontSize = 12.sp,
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomUploadPhoto(id = R.drawable.gallery_icon, onClick = {}, text = "From Gallery")
            Spacer(modifier = Modifier.height(20.dp))
            CustomUploadPhoto(id = R.drawable.camera_icon, onClick = {}, text = "Take Photo")
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                GradientButton(modifier = Modifier
                    .height(56.dp)
                    .width(157.dp), text = "Next", onClick = {})
            }
        }
    }
}

@Composable
fun CustomUploadPhoto(onClick: () -> Unit = { }, id: Int, text: String) {
    Card(
        modifier = Modifier
            .shadow(
                color = Color(0xFF5A6CEA).copy(alpha = 0.07f),
                spread = 25.dp,
                blurRadius = 50.dp,
            )
            .clickable { onClick() },
        shape = RoundedCornerShape(22),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .background(color = Color.White),
            contentAlignment = Alignment.Center,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = id),
                    contentDescription = "TNT"
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = text, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpProcessPreview() {
    FoodDeliveryTheme {
        UploadPhotoScreen(rememberNavController())
    }
}