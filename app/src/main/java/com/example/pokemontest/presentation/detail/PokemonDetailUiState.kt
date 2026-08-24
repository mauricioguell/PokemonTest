package com.example.pokemontest.presentation.detail

import com.example.pokemontest.domain.model.PokemonDetail

sealed interface PokemonDetailUiState {

    data object Loading : PokemonDetailUiState

    data class Success(
        val pokemon: PokemonDetail
    ) : PokemonDetailUiState

    data class Error(
        val message: String
    ) : PokemonDetailUiState
}