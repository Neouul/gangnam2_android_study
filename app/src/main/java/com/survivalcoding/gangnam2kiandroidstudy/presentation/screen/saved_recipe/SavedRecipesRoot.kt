package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SavedRecipesRoot(
    onCardClick: (Long) -> Unit,
    viewModel: SavedRecipesViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SavedRecipesScreen(
        state = state,
        onCardClick = { recipeId -> onCardClick(recipeId) },
    )
}