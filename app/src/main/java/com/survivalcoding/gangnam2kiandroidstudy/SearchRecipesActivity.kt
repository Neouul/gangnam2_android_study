package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe.SearchRecipesScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe.SearchRecipesViewModel

class SearchRecipesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: SearchRecipesViewModel =
                viewModel(factory = SearchRecipesViewModel.Factory)
            val state by viewModel.state.collectAsStateWithLifecycle()

            SearchRecipesScreen(
                state = state,
                viewModel = viewModel,
                onSearchTermChange = { viewModel.updateSearchTerm(it) },
                onBackClick = {},
            )
        }
    }
}