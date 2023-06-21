package com.example.myapplication.di

import com.example.myapplication.RickMortyRepository
import com.example.myapplication.data.RickMortyApiService
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
    fun provideRickMortyApi(): RickMortyApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://rickandmortyapi.com/api/")
            .build()
            .create(RickMortyApiService::class.java)
    }
    @Singleton
    @Provides
    fun provideRickMortyRepository(api: RickMortyApiService) = RickMortyRepository(api = api)
}