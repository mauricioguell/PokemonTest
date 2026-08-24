package com.example.pokemontest.domain.usecase

import com.example.pokemontest.domain.model.PokemonDetail
import com.example.pokemontest.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonDetail @Inject constructor(
    private val repository: PokemonRepository
) {

    suspend operator fun invoke(
        pokemonId: Int
    ): PokemonDetail {
        return repository.getPokemonDetail(
            id = pokemonId
        )
    }
}