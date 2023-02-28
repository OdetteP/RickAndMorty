package com.example.myapplication.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.CharacterService
import com.example.myapplication.api.models.CharacterData
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailViewModel : ViewModel() {
    val characters = MutableLiveData<CharacterData>()
    val errorMessage = MutableLiveData<String>()
    fun refreshData(characterId: Int) {
        viewModelScope.launch {
            try {
                characters.value = CharacterService.instance.getCharacter(characterId).body()
            } catch (e: Exception) {
                errorMessage.value = e.localizedMessage
            }
        }
    }
}

