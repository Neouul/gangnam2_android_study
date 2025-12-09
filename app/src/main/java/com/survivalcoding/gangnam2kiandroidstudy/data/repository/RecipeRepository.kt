package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.core.Result

interface RecipeRepository {
    suspend fun findRecipe(id: Long): Result<Recipe, String>
    suspend fun findRecipes(): Result<List<Recipe>, String>
}