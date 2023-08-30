package com.tnt.food_delivery.core.nav_type

import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.tnt.food_delivery.data.response.ProductResponse

class ProductNavType : NavType<ProductResponse>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): ProductResponse? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, ProductResponse::class.java)
        } else {
            @Suppress("DEPRECATION") bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): ProductResponse {
        return Gson().fromJson(value, ProductResponse::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: ProductResponse) {
        bundle.putParcelable(key, value)
    }
}