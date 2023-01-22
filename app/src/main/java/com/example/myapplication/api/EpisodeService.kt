package com.example.myapplication.api

import com.example.myapplication.api.models.Episodes
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface EpisodeService {
    @GET("episode")
    fun getEpisode(): Call<Episodes>

    companion object {
        val api = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            // Could add this in BuildConfig or String.xml
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EpisodeService::class.java)
    }
}