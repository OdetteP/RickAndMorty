package com.example.myapplication.data.respons

data class CharactersListResponse(
    val info: Info,
    val results: List<CharacterDetailResponse>
)