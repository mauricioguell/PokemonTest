package com.example.pokemontest.domain.model

data class PokemonDetail(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val imageUrl: String?,
    val types: List<String>,
    val abilities: List<String>
)
