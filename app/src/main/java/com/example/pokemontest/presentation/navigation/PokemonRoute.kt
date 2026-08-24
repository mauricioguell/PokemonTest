package com.example.pokemontest.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
data object PokemonListRoute

@Serializable
data class PokemonDetailRoute(
    val pokemonId: Int
)