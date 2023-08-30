package com.tnt.food_delivery.presentation.set_location

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import com.tnt.food_delivery.R
import com.tnt.food_delivery.ui.components.GradientButton
import com.tnt.food_delivery.ui.components.BackButton
import com.tnt.food_delivery.ui.components.shadow
import com.tnt.food_delivery.ui.theme.FoodDeliveryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetLocationScreen() {
    Scaffold {
        it
        Box {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.triangle_background),
                contentDescription = "tnt"
            )
            Column(
                modifier = Modifier.padding(start = 25.dp, end = 25.dp),
            ) {
                BackButton {

                }
                Text(
                    modifier = Modifier.padding(end = 60.dp),
                    text = "Set Your Location ",
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
                Card(
                    modifier = Modifier
                        .shadow(
                            color = Color(0xFF5A6CEA).copy(alpha = 0.07f),
                            spread = 25.dp,
                            blurRadius = 30.dp,
                            offsetY = 26.dp,
                            offsetX = 12.dp,
                        ),
                    shape = RoundedCornerShape(15),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(color = Color.White),
//                        contentAlignment = Alignment.CenterVertically,
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(all = 10.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier.padding(top = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Image(
                                    modifier = Modifier
                                        .width(33.dp)
                                        .height(33.dp),
                                    painter = painterResource(id = R.drawable.pin_icon),
                                    contentDescription = "TNT"
                                )
                                Spacer(modifier = Modifier.width(15.dp))
                                Text(
                                    text = "Your Location",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Card(
                                shape = RoundedCornerShape(22),
                                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(57.dp)
                                        .background(color = Color(0xFFF6F6F6)),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        text = "Set Location",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))
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
}

@Preview(showBackground = true)
@Composable
fun SignUpProcessPreview() {
    FoodDeliveryTheme {
        SetLocationScreen()
    }
}