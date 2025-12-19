package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeRoot(
    onNavigateToSearch: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToRecipeDetail: (Long) -> Unit,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event ->
            when (event) {
                HomeEvent.NavigateToSearch -> onNavigateToSearch()
                HomeEvent.NavigateToProfile -> onNavigateToProfile()
                is HomeEvent.NavigateToRecipeDetail -> onNavigateToRecipeDetail(event.recipeId)
            }
        }
    }

    HomeScreen(
        state = state,
        onAction = { action -> viewModel.onAction(action) }
    )
}