package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

data class SavedRecipesState(
    val savedRecipes: List<Recipe> = emptyList(),
)