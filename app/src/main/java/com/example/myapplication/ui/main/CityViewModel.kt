package com.example.myapplication.ui.main

import android.app.Activity
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.Api.WeatherApi
import com.example.myapplication.Models.WeatherData
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CityViewModel : ViewModel() {

    private val weatherApi = Retrofit.Builder()
        .baseUrl("https://us-central1-mobile-assignment-server.cloudfunctions.net/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    val cities = MutableLiveData<List<WeatherData>>()

    fun refreshData() {
        val call = weatherApi.getCities()
        call.enqueue(object : Callback<List<WeatherData>> {
            override fun onResponse(
                call: Call<List<WeatherData>>,
                response: Response<List<WeatherData>>
            ) {
                cities.value = response.body()
            }

            override fun onFailure(call: Call<List<WeatherData>>, t: Throwable) {
                // Handle the error.
            }
        })

    }
}