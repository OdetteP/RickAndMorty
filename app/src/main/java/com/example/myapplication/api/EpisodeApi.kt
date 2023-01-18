package com.example.myapplication.api

import com.example.myapplication.api.models.Episodes
import retrofit2.Call
import retrofit2.http.GET

interface EpisodeApi {
    @GET("episode")
    fun getEpisode(): Call<Episodes>
}