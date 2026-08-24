package com.example.pokemontest.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PokemonSpritesResponse(
    @SerialName("front_default")
    val frontDefault: String?
)
