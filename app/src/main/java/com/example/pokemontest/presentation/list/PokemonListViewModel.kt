package com.example.pokemontest.presentation.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemontest.domain.usecase.GetPokemonList
import com.example.pokemontest.utils.TagUtils.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonList: GetPokemonList
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<PokemonListUiState>(
            PokemonListUiState.Loading
        )

    val uiState: StateFlow<PokemonListUiState> =
        _uiState.asStateFlow()

    init {
        loadPokemon()
    }

    private fun loadPokemon() {
        viewModelScope.launch {
            _uiState.value =
                PokemonListUiState.Loading
            try {
                val pokemon = getPokemonList(
                    limit = 20,
                    offset = 0
                )
                _uiState.value =
                    PokemonListUiState.Success(
                        pokemon = pokemon
                    )
            } catch (e: Exception) {
                Log.e(TAG, "Error loading Pokémon", e)
                _uiState.value =
                    PokemonListUiState.Error(
                        message = "Unable to load Pokémon"
                    )
            }
        }
    }
}