package com.example.myapplication.api

import com.example.myapplication.api.models.CharacterData
import com.example.myapplication.api.models.Characters
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {
    @GET("character/{id}")
    fun getCharacter(@Path("id") id: Int): Call<CharacterData>
}