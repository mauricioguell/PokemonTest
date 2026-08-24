package com.example.pokemontest.data.repository

import com.example.pokemontest.data.mapper.PokemonMapper
import com.example.pokemontest.data.remote.PokemonApi
import com.example.pokemontest.domain.model.Pokemon
import com.example.pokemontest.domain.model.PokemonDetail
import com.example.pokemontest.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApi,
    private val mapper: PokemonMapper
) : PokemonRepository {

    override suspend fun getPokemon(
        limit: Int,
        offset: Int
    ): List<Pokemon> {
        val response = api.getPokemon(
            limit = limit,
            offset = offset
        )
        return mapper.map(response)
    }

    override suspend fun getPokemonDetail(
        id: Int
    ): PokemonDetail {
        return mapper.map(
            api.getPokemonDetail(id)
        )
    }
}
