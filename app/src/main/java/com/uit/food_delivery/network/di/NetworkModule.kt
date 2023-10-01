package com.uit.food_delivery.network.di

import com.uit.food_delivery.network.api.AuthService
import com.uit.food_delivery.network.api.RestaurantService
import com.uit.food_delivery.network.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    private val retrofit = RetrofitModule.provideRetrofit()

    @Singleton
    @Provides
    fun provideUserApi(): UserApi = retrofit.create(UserApi::class.java)

    @Singleton
    @Provides
    fun provideAuthService(): AuthService = retrofit.create(AuthService::class.java)

    @Singleton
    @Provides
    fun provideRestaurantService(): RestaurantService =
        retrofit.create(RestaurantService::class.java)
}