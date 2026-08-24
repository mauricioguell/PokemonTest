package com.example.pokemontest.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import com.example.pokemontest.domain.model.PokemonDetail
import com.example.pokemontest.ui.components.ErrorContent
import com.example.pokemontest.ui.components.LoadingContent

@Composable
fun PokemonDetailScreen(
    onBackClick: () -> Unit,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PokemonDetailContent(
        uiState = uiState,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PokemonDetailContent(
    uiState: PokemonDetailUiState,
    onBackClick: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Pokémon detail")
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        when (uiState) {

            PokemonDetailUiState.Loading -> {
                LoadingContent(
                    modifier =
                        Modifier.padding(paddingValues)
                )
            }

            is PokemonDetailUiState.Success -> {
                PokemonDetail(
                    pokemon = uiState.pokemon,
                    modifier =
                        Modifier.padding(paddingValues)
                )
            }

            is PokemonDetailUiState.Error -> {
                ErrorContent(
                    message = uiState.message,
                    modifier =
                        Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Composable
private fun PokemonDetail(
    pokemon: PokemonDetail,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        pokemon.imageUrl?.let { imageUrl ->

            AsyncImage(
                model = imageUrl,
                contentDescription = pokemon.name,
                modifier = Modifier.size(200.dp)
            )
        }

        Text(
            text = pokemon.name,
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "#${pokemon.id}",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        HorizontalDivider()

        Text(
            text = "Height: ${pokemon.height}"
        )

        Text(
            text = "Weight: ${pokemon.weight}"
        )

        Text(
            text = "Types: ${pokemon.types.joinToString()}"
        )

        Text(
            text = "Abilities: ${pokemon.abilities.joinToString()}"
        )
    }
}