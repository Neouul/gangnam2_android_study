package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.distinctUntilChanged
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SavedRecipesRoot(
    onCardClick: (Long) -> Unit,
    viewModel: SavedRecipesViewModel = koinViewModel(),
    onReachedBottom: suspend () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()     // 스크롤 위치 추적

    LaunchedEffect(listState) {
        snapshotFlow { listState.isScrolledToBottom() }
            .distinctUntilChanged()
            .collect { isBottom ->
                if (isBottom) {
                    println("log: last item")
                    onReachedBottom()
                }
            }
    }

    SavedRecipesScreen(
        state = state,
        listState = listState,
        onCardClick = { recipeId -> onCardClick(recipeId) },
    )
}

private fun LazyListState.isScrolledToBottom(): Boolean {
    val lastVisibleItemIndex =
        layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: return false

    return lastVisibleItemIndex == layoutInfo.totalItemsCount - 1
}