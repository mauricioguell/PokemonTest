package com.example.pokemontest.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypeSlotResponse(
    val type: PokemonNamedResourceResponse
)
