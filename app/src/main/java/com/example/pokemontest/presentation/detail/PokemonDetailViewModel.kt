package com.example.pokemontest.presentation.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.pokemontest.domain.usecase.GetPokemonDetail
import com.example.pokemontest.presentation.navigation.PokemonDetailRoute
import com.example.pokemontest.utils.TagUtils.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetail: GetPokemonDetail,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val route =
        savedStateHandle.toRoute<PokemonDetailRoute>()

    private val _uiState =
        MutableStateFlow<PokemonDetailUiState>(
            PokemonDetailUiState.Loading
        )

    val uiState =
        _uiState.asStateFlow()

    init {
        loadPokemon(route.pokemonId)
    }

    private fun loadPokemon(
        pokemonId: Int
    ) {
        viewModelScope.launch {
            _uiState.value =
                PokemonDetailUiState.Loading
            try {
                val pokemon =
                    getPokemonDetail(pokemonId)
                _uiState.value =
                    PokemonDetailUiState.Success(
                        pokemon
                    )
            } catch (e: Exception) {
                Log.d(TAG, "Error loading Pokémon", e)
                _uiState.value =
                    PokemonDetailUiState.Error(
                        message = "Unable to load Pokémon"
                    )
            }
        }
    }
}