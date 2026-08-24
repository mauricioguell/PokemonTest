package com.example.pokemontest.domain.usecase

import com.example.pokemontest.domain.model.Pokemon
import com.example.pokemontest.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonList @Inject constructor(
    private val repository: PokemonRepository
) {

    suspend operator fun invoke(
        limit: Int,
        offset: Int
    ): List<Pokemon> {
        return repository.getPokemon(
            limit = limit,
            offset = offset
        )
    }
}