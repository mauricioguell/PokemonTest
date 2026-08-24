package com.example.pokemontest.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemontest.presentation.detail.PokemonDetailScreen
import com.example.pokemontest.presentation.list.PokemonListScreen
import com.example.pokemontest.presentation.navigation.PokemonDetailRoute
import com.example.pokemontest.presentation.navigation.PokemonListRoute

@Composable
fun PokemonApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PokemonListRoute
    ) {

        composable<PokemonListRoute> {
            PokemonListScreen(
                onPokemonClick = { pokemonId ->
                    navController.navigate(
                        PokemonDetailRoute(
                            pokemonId = pokemonId
                        )
                    )
                }
            )
        }

        composable<PokemonDetailRoute> {
            PokemonDetailScreen(
                onBackClick = navController::navigateUp
            )
        }
    }
}