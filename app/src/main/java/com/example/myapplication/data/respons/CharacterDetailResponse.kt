package com.example.myapplication.data.respons

data class CharacterDetailResponse(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val url: String
)