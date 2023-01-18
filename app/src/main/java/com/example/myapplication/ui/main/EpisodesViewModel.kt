package com.example.myapplication.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.EpisodeApi
import com.example.myapplication.api.models.Episodes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EpisodesViewModel : ViewModel() {

    private val episodeApi = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(EpisodeApi::class.java)

    val episodes = MutableLiveData<Episodes>()

    fun refreshData() {
        Log.d("Odette", "refreshData" )
        val call = episodeApi.getEpisode()
        call.enqueue(object : Callback<Episodes> {
            override fun onResponse(
                call: Call<Episodes>,
                response: Response<Episodes>
            ) {
                Log.d("Odette", "Respons " + response.body())
                episodes.value = response.body()
            }

            override fun onFailure(call: Call<Episodes>, t: Throwable) {
                Log.d("Odette", "Failed " +t.message )
            }
        })

    }
}