package com.example.pokemontest.presentation.list

import com.example.pokemontest.domain.model.Pokemon

sealed interface PokemonListUiState {

    data object Loading : PokemonListUiState

    data class Success(
        val pokemon: List<Pokemon>
    ) : PokemonListUiState

    data class Error(
        val message: String
    ) : PokemonListUiState
}