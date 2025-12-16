package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication

@Composable
fun SavedRecipesRoot(
    onCardClick: (Long) -> Unit,
    viewModel: SavedRecipesViewModel = viewModel(
        factory = SavedRecipesViewModel.factory(
            LocalContext.current.applicationContext as AppApplication
        )
    ),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    SavedRecipesScreen(
        state = state,
        onCardClick = { recipeId -> onCardClick(recipeId) },
    )
}