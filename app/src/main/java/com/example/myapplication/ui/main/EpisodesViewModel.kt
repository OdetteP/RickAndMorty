package com.example.myapplication.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.EpisodeService
import com.example.myapplication.api.models.EpisodeData
import com.example.myapplication.api.models.Episodes
import kotlinx.coroutines.launch

class EpisodesViewModel : ViewModel() {
    val episodes = MutableLiveData<Episodes>()
    val errorMessage = MutableLiveData<String>()

    fun refreshData() {
        viewModelScope.launch {
            try {
                episodes.value = EpisodeService.instance.getEpisode().body()
            } catch (e: Exception) {
                errorMessage.value = e.localizedMessage
            }
        }
    }
}