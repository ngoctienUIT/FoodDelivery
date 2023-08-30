package com.tnt.food_delivery.core.nav_type

import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.tnt.food_delivery.data.response.UserResponse

class UserNavType : NavType<UserResponse>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): UserResponse? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, UserResponse::class.java)
        } else {
            @Suppress("DEPRECATION") bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): UserResponse {
        return Gson().fromJson(value, UserResponse::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: UserResponse) {
        bundle.putParcelable(key, value)
    }
}