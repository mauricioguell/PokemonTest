package com.example.pokemontest.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pokemontest.domain.model.Pokemon
import com.example.pokemontest.ui.components.LoadingContent
import com.example.pokemontest.ui.components.ErrorContent

@Composable
fun PokemonListScreen(
    onPokemonClick: (Int) -> Unit,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PokemonListContent(
        uiState = uiState,
        onPokemonClick = onPokemonClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PokemonListContent(
    uiState: PokemonListUiState,
    onPokemonClick: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Pokémon")
                }
            )
        }
    ) { paddingValues ->

        when (uiState) {

            PokemonListUiState.Loading -> {
                LoadingContent(
                    modifier = Modifier.padding(paddingValues)
                )
            }

            is PokemonListUiState.Success -> {
                PokemonList(
                    pokemon = uiState.pokemon,
                    onPokemonClick = onPokemonClick,
                    modifier = Modifier.padding(paddingValues)
                )
            }

            is PokemonListUiState.Error -> {
                ErrorContent(
                    message = uiState.message,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Composable
private fun PokemonList(
    pokemon: List<Pokemon>,
    onPokemonClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = pokemon,
            key = { it.id },
        ) { pokemon ->

            PokemonListItem(
                pokemon = pokemon,
                onClick = {
                    onPokemonClick(pokemon.id)
                }
            )
        }
    }
}

@Composable
private fun PokemonListItem(
    pokemon: Pokemon,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        ListItem(
            headlineContent = {
                Text(pokemon.name)
            },
            leadingContent = {
                Text("#${pokemon.id}")
            }
        )
    }
}

@Preview
@Composable
private fun PokemonListScreenPreview() {
    PokemonListContent(
        uiState = PokemonListUiState.Success(
            pokemon = listOf(
                Pokemon(
                    id = 1,
                    name = "Bulbasaur"
                ),
                Pokemon(
                    id = 2,
                    name = "Ivysaur"
                ),
                Pokemon(
                    id = 3,
                    name = "Venusaur"
                )
            )
        ),
        onPokemonClick = {}
    )
}
