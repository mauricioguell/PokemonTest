package com.example.pokemontest.domain.repository

import com.example.pokemontest.domain.model.Pokemon
import com.example.pokemontest.domain.model.PokemonDetail

interface PokemonRepository {

    suspend fun getPokemon(
        limit: Int,
        offset: Int
    ): List<Pokemon>

    suspend fun getPokemonDetail(
        id: Int
    ): PokemonDetail
}