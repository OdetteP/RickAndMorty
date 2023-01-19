package com.example.myapplication.ui.main

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.CharacterService
import com.example.myapplication.api.models.CharacterData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.util.jar.Manifest

class CharacterDetailViewModel : ViewModel() {
    val characters = MutableLiveData<CharacterData>()
    val errorMessage = MutableLiveData<String>()

    fun refreshData(characterId: Int) {
        val call = CharacterService.api.getCharacter(characterId)
        call.enqueue(object : Callback<CharacterData> {
            override fun onResponse(
                call: Call<CharacterData>,
                response: Response<CharacterData>
            ) {
                characters.value = response.body()
            }
            override fun onFailure(call: Call<CharacterData>, t: Throwable) {
                errorMessage.value = t.message
            }
        })
    }
}

