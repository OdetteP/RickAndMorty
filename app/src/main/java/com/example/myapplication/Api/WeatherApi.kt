package com.example.myapplication.Api

import com.example.myapplication.Models.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface WeatherApi {
    @GET("weather")
    fun getCities(): Call<List<WeatherData>>
}