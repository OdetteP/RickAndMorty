package com.example.myapplication.api

import com.example.myapplication.api.models.Episodes
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface EpisodeService {
    @GET("episode")
    suspend fun getEpisode(): Response<Episodes>

    companion object {

        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val instance: EpisodeService by lazy { retrofit.create(EpisodeService::class.java) }
    }
}