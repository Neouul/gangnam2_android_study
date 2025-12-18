package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeRoot(
    navigateToSearch: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToRecipeDetail: (Long) -> Unit,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        state = state,
        onAction = { action ->
            viewModel.onAction(
                action = action,
                navigateToSearch = navigateToSearch,
                navigateToProfile = navigateToProfile,
                navigateToRecipeDetail = navigateToRecipeDetail,
            )
        }
    )
}