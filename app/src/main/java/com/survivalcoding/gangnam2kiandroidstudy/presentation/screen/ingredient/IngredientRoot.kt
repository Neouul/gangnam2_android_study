package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication

@Composable
fun IngredientRoot(
    recipeId: Long,
    onBackClick: () -> Unit,
    viewModel: IngredientViewModel = viewModel(
        factory = IngredientViewModel.factory(
            LocalContext.current.applicationContext as AppApplication
        )
    ),
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