package com.example.myapplication.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.WeatherApi
import com.example.myapplication.model.Episodes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CityViewModel : ViewModel() {

    private val weatherApi = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    val cities = MutableLiveData<Episodes>()

    fun refreshData() {
        Log.d("Odette", "refreshData" )
        val call = weatherApi.getCities()
        call.enqueue(object : Callback<Episodes> {
            override fun onResponse(
                call: Call<Episodes>,
                response: Response<Episodes>
            ) {
                Log.d("Odette", "Respons " + response.body())
                cities.value = response.body()
            }

            override fun onFailure(call: Call<Episodes>, t: Throwable) {
                Log.d("Odette", "Failed " +t.message )
            }
        })

    }
}