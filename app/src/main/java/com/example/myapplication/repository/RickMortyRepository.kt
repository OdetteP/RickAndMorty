package com.example.myapplication

import com.example.myapplication.data.RickMortyApi
import com.example.myapplication.data.respons.CharacterDetailResponse
import com.example.myapplication.data.respons.CharactersListResponse
import com.example.myapplication.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@ActivityScoped
class RickMortyRepository @Inject constructor(
    private val api: RickMortyApi
) {
    suspend fun getCharactersList(page: Int): Resource<CharactersListResponse> =
        withContext(Dispatchers.IO) {
            val response = try {
                api.getCharactersList(page = page)
            } catch (e: Exception) {
                Timber.e(e)
                return@withContext Resource.Error(e.localizedMessage ?: "An error occurred")
            }
            return@withContext Resource.Success(response)
        }

    suspend fun getCharacterDetails(characterId: Int): Resource<CharacterDetailResponse> =
        withContext(Dispatchers.IO) {
            val response = try {
                api.getCharacterDetails(id = characterId)
            } catch (e: Exception) {
                Timber.e(e)
                return@withContext Resource.Error(e.localizedMessage ?: "An error occurred")
            }
            return@withContext Resource.Success(response)
        }
}