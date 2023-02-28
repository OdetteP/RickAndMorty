package com.example.myapplication.api

import com.example.myapplication.api.models.CharacterData
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {
    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<CharacterData>

    companion object {

        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val instance: CharacterService by lazy { retrofit.create(CharacterService::class.java) }
    }
}

