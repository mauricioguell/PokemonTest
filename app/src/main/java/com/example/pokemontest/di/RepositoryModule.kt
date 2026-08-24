package com.example.pokemontest.di

import com.example.pokemontest.data.repository.PokemonRepositoryImpl
import com.example.pokemontest.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPokemonRepository(
        implementation: PokemonRepositoryImpl
    ): PokemonRepository
}