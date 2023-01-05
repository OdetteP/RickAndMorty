package com.example.myapplication.data

import com.example.myapplication.model.WeatherData
import retrofit2.Call
import retrofit2.http.GET

interface WeatherApi {
    @GET("weather")
    fun getCities(): Call<List<WeatherData>>
}