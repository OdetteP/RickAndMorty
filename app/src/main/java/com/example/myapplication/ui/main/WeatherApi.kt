package com.example.myapplication.ui.main

import retrofit2.Call
import retrofit2.http.GET

interface WeatherApi {
    @GET("weather")
    fun getCities(): Call<List<WeatherData>>
}