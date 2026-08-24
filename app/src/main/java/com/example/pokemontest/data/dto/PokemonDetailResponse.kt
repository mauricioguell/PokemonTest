package com.example.pokemontest.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: PokemonSpritesResponse,
    val types: List<PokemonTypeSlotResponse>,
    val abilities: List<PokemonAbilitySlotResponse>
)
