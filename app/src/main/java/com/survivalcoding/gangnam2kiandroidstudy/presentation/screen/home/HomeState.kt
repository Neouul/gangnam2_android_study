package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

data class HomeState(
    val allRecipes: List<Recipe> = emptyList(),
    val selectedRecipes: List<Recipe> = emptyList(),
    val selectedCategory: String = "All",
    val searchTerm: String = "",
    val isLoading: Boolean = false,
)