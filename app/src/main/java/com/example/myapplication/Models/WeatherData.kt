package com.example.myapplication.Models

data class WeatherData(
    val date: String,
    val city: CityData,
    val tempType: String,
    val temp: Double
)