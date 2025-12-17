package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun IngredientRoot(
    recipeId: Long,
    onBackClick: () -> Unit,
    viewModel: IngredientViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(recipeId) {
        viewModel.loadRecipeDetail(recipeId)
        viewModel.loadProcedure(recipeId)
    }

    IngredientScreen(
        state = state,
        onBackClick = onBackClick,
        onTapClick = { index -> viewModel.updateTabIndex(index) }
    )
}