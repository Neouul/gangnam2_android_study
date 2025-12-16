package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

data class SearchRecipesState(
    val allRecipes: List<Recipe> = emptyList(),
    val filteredRecipes: List<Recipe> = emptyList(),
    val searchTerm: String = "",
    val isLoading: Boolean = false,
)
