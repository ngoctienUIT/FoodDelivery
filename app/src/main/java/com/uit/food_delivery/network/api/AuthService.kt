package com.uit.food_delivery.network.api

import com.uit.food_delivery.data.response.AuthenticationResponse
import com.uit.food_delivery.data.response.RegisterResponse
import com.uit.food_delivery.data.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    //    @Headers("Content-Type: application/json")
    @POST("user/login")
    suspend fun login(@Body body: Map<String, String>): Response<AuthenticationResponse>

    @POST("user/signup")
    suspend fun signup(@Body body: @JvmSuppressWildcards Map<String, Any>): Response<UserResponse>

    @POST("user/check-register")
    suspend fun checkRegister(@Body body: Map<String, String>): Response<RegisterResponse>
}