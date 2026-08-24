package com.example.pokemontest.data.mapper

import com.example.pokemontest.data.dto.PokemonDetailResponse
import com.example.pokemontest.data.dto.PokemonListResponse
import com.example.pokemontest.data.dto.PokemonResponse
import com.example.pokemontest.domain.model.Pokemon
import com.example.pokemontest.domain.model.PokemonDetail
import javax.inject.Inject

class PokemonMapper @Inject constructor() {

    fun map(response: PokemonListResponse): List<Pokemon> {
        return response.results.map(::map)
    }

    fun map(response: PokemonResponse): Pokemon {
        return Pokemon(
            id = extractId(response.url),
            name = response.name.replaceFirstChar { it.uppercase() }
        )
    }

    fun map(
        response: PokemonDetailResponse
    ): PokemonDetail {
        return PokemonDetail(
            id = response.id,
            name = response.name.replaceFirstChar {
                it.uppercase()
            },
            height = response.height,
            weight = response.weight,
            imageUrl = response.sprites.frontDefault,
            types = response.types.map {
                it.type.name.replaceFirstChar { char ->
                    char.uppercase()
                }
            },
            abilities = response.abilities.map {
                it.ability.name.replaceFirstChar { char ->
                    char.uppercase()
                }
            }
        )
    }

    private fun extractId(url: String): Int {
        return url
            .trimEnd('/')
            .substringAfterLast('/')
            .toInt()
    }
}