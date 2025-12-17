package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchBottomSheet


@Composable
fun SearchRecipesRoot(
    onBackClick: () -> Unit,
    viewModel: SearchRecipesViewModel = hiltViewModel(),
) {
    // UI 상태
    val state by viewModel.state.collectAsStateWithLifecycle()
    // 바텀시트에서 선택한 필터 상태
    val filterState by viewModel.filterState.collectAsStateWithLifecycle()
    // 필터 시트 출력 여부
    var showFilterSheet by remember { mutableStateOf(false) }

    SearchRecipesScreen(
        state = state,
        onSearchTermChange = { viewModel.updateSearchTerm(it) },
        onBackClick = onBackClick,
        onFilterClick = { showFilterSheet = true }
    )

    // 필터 시트
    if (showFilterSheet) {
        FilterSearchBottomSheet(
            onApplyFilter = { filter ->
                viewModel.applyFilter(filter)
                showFilterSheet = false
            },
            onDismiss = { showFilterSheet = false },
            initialFilter = filterState,
        )
    }
}