package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.MockRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockBookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase

@Composable
fun SavedRecipesRoot(
    onCardClick: (Long) -> Unit,
    viewModel: SavedRecipesViewModel = viewModel(
        factory = SavedRecipesViewModel.factory(
            application = LocalContext.current.applicationContext as AppApplication,
            useCase = GetSavedRecipesUseCase(bookmarkRepository = MockBookmarkRepositoryImpl(), recipeRepository =  RecipeRepositoryImpl(
                MockRecipeDataSourceImpl())),
        ),
    ),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SavedRecipesScreen(
        state = state,
        onCardClick = { recipeId -> onCardClick(recipeId) },
    )
}