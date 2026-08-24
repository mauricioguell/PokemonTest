package com.example.pokemontest.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonAbilitySlotResponse(
    val ability: PokemonNamedResourceResponse
)
