package com.example.myapplication.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.EpisodeService
import com.example.myapplication.api.models.Episodes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisodesViewModel : ViewModel() {
    val episodes = MutableLiveData<Episodes>()
    val errorMessage = MutableLiveData<String>()

    fun refreshData() {
        val call = EpisodeService.api.getEpisode()
        call.enqueue(object : Callback<Episodes> {
            override fun onResponse(
                call: Call<Episodes>,
                response: Response<Episodes>
            ) {
                episodes.value = response.body()
            }
            override fun onFailure(call: Call<Episodes>, t: Throwable) {
                errorMessage.value = t.message
            }
        })
    }
}