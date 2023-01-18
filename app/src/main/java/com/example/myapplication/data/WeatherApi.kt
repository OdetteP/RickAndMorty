package com.example.myapplication.data

import com.example.myapplication.model.Episodes
import retrofit2.Call
import retrofit2.http.GET

interface WeatherApi {
    @GET("episode")
    fun getCities(): Call<Episodes>
}