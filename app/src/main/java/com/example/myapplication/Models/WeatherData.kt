package com.example.myapplication.Models

import com.example.myapplication.Models.CityData

data class WeatherData(
    val date: String,
    val city: CityData,
    val tempType: String,
    val temp: Double
)