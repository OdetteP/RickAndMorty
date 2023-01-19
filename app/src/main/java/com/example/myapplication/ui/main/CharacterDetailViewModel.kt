package com.example.myapplication.ui.main

import android.content.Context
import android.os.Environment
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.CharacterService
import com.example.myapplication.api.models.CharacterData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream

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

    fun exportCharacterDetails(context: Context, character: CharacterData) {
        try {
            val file = File(Environment.getExternalStorageDirectory(), "character_details.txt")
            val outputStream = FileOutputStream(file)

            val data = "Name: ${character.name}\nStatus: ${character.status}" +
                    "\nSpecies: ${character.species}\nOrigin: ${character.origin.name}"
            outputStream.write(data.toByteArray())
            outputStream.close()

            Toast.makeText(context, "File exported to ${file.absolutePath}", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}

