package com.example.myapplication.model

data class WeatherData(
    val date: String,
    val city: CityData,
    val tempType: String,
    val temp: Double
)