package com.uit.food_delivery.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uit.food_delivery.core.utils.EventResults
import com.uit.food_delivery.core.utils.EventStatus
import com.uit.food_delivery.data.response.ProductResponse
import com.uit.food_delivery.data.response.UserResponse
import com.uit.food_delivery.network.api.RestaurantService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val restaurantService: RestaurantService ): ViewModel() {
    private val _restaurant: MutableStateFlow<EventResults<List<UserResponse>>> =
        MutableStateFlow(EventResults())
    val restaurant: StateFlow<EventResults<List<UserResponse>>> = _restaurant

    private val _product: MutableStateFlow<EventResults<List<ProductResponse>>> =
        MutableStateFlow(EventResults())
    val product: StateFlow<EventResults<List<ProductResponse>>> = _product

    suspend fun getAllRestaurant() {
        Log.d("test", "get all restaurant")
        _restaurant.value = EventResults(status = EventStatus.LOADING)
        viewModelScope.launch {
            try {
                val data = restaurantService.searchRestaurant("")
                if (data.isSuccessful) {
                    _restaurant.value =
                        EventResults(status = EventStatus.SUCCESS, data = data.body())
//                    delay(1000)
//                    _restaurant.value = EventResults()
                    Log.d("data", data.body().toString())
                } else {
                    val errMsg = data.errorBody()?.string() ?: run { data.code().toString() }
                    _restaurant.value =
                        EventResults(status = EventStatus.ERROR, error = errMsg)
                    Log.d("error data", errMsg)
                }
            } catch (e: Exception) {
                println(e.message.toString())
                _restaurant.value = EventResults(status = EventStatus.ERROR, error = e.message)
                Log.d("error", e.message.toString())
            }
        }
    }

    suspend fun getAllProduct() {
        Log.d("test", "get all product")
        _product.value = EventResults(status = EventStatus.LOADING)
        viewModelScope.launch {
            try {
                val data = restaurantService.searchProduct("")
                if (data.isSuccessful) {
                    _product.value =
                        EventResults(status = EventStatus.SUCCESS, data = data.body())
//                    delay(1000)
//                    _product.value = EventResults()
                    Log.d("data", data.body().toString())
                } else {
                    val errMsg = data.errorBody()?.string() ?: run { data.code().toString() }
                    _product.value =
                        EventResults(status = EventStatus.ERROR, error = errMsg)
                    Log.d("error data", errMsg)
                }
            } catch (e: Exception) {
                println(e.message.toString())
                _product.value = EventResults(status = EventStatus.ERROR, error = e.message)
                Log.d("error", e.message.toString())
            }
        }
    }
}