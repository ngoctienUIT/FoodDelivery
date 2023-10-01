package com.uit.food_delivery

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(16 /*WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE*/)
        setContent { FoodDelivery() }
    }
}