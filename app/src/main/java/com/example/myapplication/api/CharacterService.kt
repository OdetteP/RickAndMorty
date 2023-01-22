package com.example.myapplication.api

import com.example.myapplication.api.models.CharacterData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {
    @GET("character/{id}")
    fun getCharacter(@Path("id") id: Int): Call<CharacterData>

    companion object {
        val api = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            // Could add this in BuildConfig or String.xml
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharacterService::class.java)
    }
}
