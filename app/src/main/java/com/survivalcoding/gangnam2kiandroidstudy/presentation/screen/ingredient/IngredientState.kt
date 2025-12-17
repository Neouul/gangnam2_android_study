package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory

data class IngredientState(
    val recipe: Recipe = Recipe(
        id = 0,
        category = RecipeCategory.NONE,
        name = "",
        imageUrl = "",
        chef = "",
        time = "",
        rating = 0.0,
        ingredients = emptyList(),
    ),
    val tapIndex: Int = 0,
    val procedures: List<String> = emptyList(),
)