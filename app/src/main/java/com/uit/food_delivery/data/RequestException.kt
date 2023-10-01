package com.uit.food_delivery.data

class RequestException(val code: Int, message: String) : Throwable(message)