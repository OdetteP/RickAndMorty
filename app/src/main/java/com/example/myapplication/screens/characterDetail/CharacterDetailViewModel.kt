package com.example.myapplication.screens.characterDetail

import androidx.lifecycle.ViewModel
import com.example.myapplication.RickMortyRepository
import com.example.myapplication.data.respons.CharacterDetailResponse
import com.example.myapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: RickMortyRepository
) : ViewModel() {

    suspend fun getCharacterDetail(id: Int): Resource<CharacterDetailResponse> {
        return repository.getCharacterDetails(id)
    }
}