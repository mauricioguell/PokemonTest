package com.example.pokemontest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.pokemontest.presentation.PokemonApp
import com.example.pokemontest.ui.theme.PokemonTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonTestTheme {
                PokemonApp()
            }
        }
    }
}
