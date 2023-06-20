package com.example.myapplication.di

import com.example.myapplication.RickMortyRepository
import com.example.myapplication.data.RickMortyApi
import com.example.myapplication.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRickMortyApi(): RickMortyApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://rickandmortyapi.com/api/")
            .build()
            .create(RickMortyApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRickMortyRepository(api: RickMortyApi) = RickMortyRepository(api = api)
}