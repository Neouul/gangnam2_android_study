package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeRoot(
    onSearchClick: () -> Unit,
    onProfileClick: () -> Unit,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        state = state,
        onCategoryClick = { category -> viewModel.onSelectCategory(category) },
        onBookmarkClick = { recipeId -> viewModel.toggleBookmark(recipeId) },
        onSearchClick = onSearchClick,
        onProfileClick = onProfileClick,
    )
}