package com.example.myapplication.ui.main

data class WeatherData(
    val date: String,
    val city: City,
    val tempType: String,
    val temp: Double
)