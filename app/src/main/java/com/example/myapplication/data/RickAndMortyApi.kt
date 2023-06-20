package com.example.myapplication.data
import com.example.myapplication.data.models.CharacterData
import com.example.myapplication.data.models.Episodes
import com.example.myapplication.data.respons.CharacterDetailResponse
import com.example.myapplication.data.respons.CharactersListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickMortyApi {

    @GET("character")
    suspend fun getCharactersList(@Query("page") page: Int): CharactersListResponse

    @GET("character/{id}")
    suspend fun getCharacterDetails(@Path("id") id: Int): CharacterDetailResponse
}