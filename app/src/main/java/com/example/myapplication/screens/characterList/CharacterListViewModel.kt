package com.example.myapplication.screens.characterList

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.RickMortyRepository
import com.example.myapplication.data.models.CharacterListEntry
import com.example.myapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository: RickMortyRepository
) : ViewModel() {

    private var currentPage = 1

    var characterList = mutableStateOf<List<CharacterListEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var isEndOfList = mutableStateOf(false)

    private var cachedCharactersList = listOf<CharacterListEntry>()
    private var isSearchStarting = true
    var isSearching = mutableStateOf(false)


    init {
        loadPaginatedCharacters()
    }

    fun searchCharacterList(query: String) {
        val listToSearch = if (isSearchStarting) {
            characterList.value
        } else {
            cachedCharactersList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                characterList.value = cachedCharactersList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.characterName.contains(
                    query.trim(),
                    ignoreCase = true
                ) || it.characterId.toString() == query.trim()
            }
            if (isSearchStarting) {
                cachedCharactersList = characterList.value
                isSearchStarting = false
            }
            characterList.value = results
            isSearching.value = true
        }
    }

    fun loadPaginatedCharacters() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getCharactersList(currentPage)
            when (result) {
                is Resource.Success -> {
                    isEndOfList.value =
                        currentPage * 20 >= result.data!!.info.count
                    val entries = result.data.results.mapIndexed { _, response ->
                        CharacterListEntry(
                            characterId = response.id,
                            characterName = response.name,
                            imageUrl = response.image
                        )
                    }
                    currentPage++
                    loadError.value = ""
                    isLoading.value = false
                    characterList.value += entries
                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
                is Resource.Loading -> TODO()
            }
        }
    }
}