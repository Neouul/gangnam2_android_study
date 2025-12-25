package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

class FakeRecipeRepository : RecipeRepository {
    var shouldReturnError = false

    private val recipes = listOf(
        Recipe(
            id = 1,
            category = RecipeCategory.ASIAN,
            name = "Test Recipe 1",
            imageUrl = "",
            chef = "Chef 1",
            time = "10 min",
            rating = 4.5,
            ingredients = emptyList()
        ),
        Recipe(
            id = 2,
            category = RecipeCategory.ASIAN,
            name = "Test Recipe 2",
            imageUrl = "",
            chef = "Chef 2",
            time = "20 min",
            rating = 4.0,
            ingredients = emptyList()
        )
    )

    override suspend fun findRecipe(id: Long): Result<Recipe, String> {
        if (shouldReturnError) {
            return Result.Error("Error")
        }
        val recipe = recipes.find { it.id == id }
        return if (recipe != null) {
            Result.Success(recipe)
        } else {
            Result.Error("Not Found")
        }
    }

    override suspend fun findRecipes(): Result<List<Recipe>, String> {
        if (shouldReturnError) {
            return Result.Error("Error")
        }
        return Result.Success(recipes)
    }
}
