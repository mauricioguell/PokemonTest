package com.example.pokemontest.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonNamedResourceResponse(
    val name: String,
    val url: String
)
