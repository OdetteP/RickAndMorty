package com.example.myapplication.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.CharacterApi
import com.example.myapplication.api.EpisodeApi
import com.example.myapplication.api.models.CharacterData
import com.example.myapplication.api.models.Characters
import com.example.myapplication.api.models.Episodes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharactersViewModel : ViewModel() {
    private val charactersApi = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CharacterApi::class.java)

    val characters = MutableLiveData<CharacterData>()

        fun refreshData(characterId: Int) {
            val call = charactersApi.getCharacter(characterId)
            call.enqueue(object : Callback<CharacterData> {
                override fun onResponse(
                    call: Call<CharacterData>,
                    response: Response<CharacterData>
                ) {

                    Log.d("Odette", response.body().toString())
                    characters.value = response.body()
                }

                override fun onFailure(call: Call<CharacterData>, t: Throwable) {
                    Log.d("Odette", "Failed " + t.message)
                }
            })

        }
    }

