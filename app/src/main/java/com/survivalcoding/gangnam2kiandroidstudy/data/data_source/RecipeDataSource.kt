package com.survivalcoding.gangnam2kiandroidstudy.data.data_source

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

interface RecipeDataSource{
    suspend fun getRecipe(id: Long): Recipe
    suspend fun getRecipes(): List<Recipe>
}