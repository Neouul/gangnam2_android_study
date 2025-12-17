package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import javax.inject.Inject

class GetSavedRecipesUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
    private val recipeRepository: RecipeRepository,
) {
    suspend fun execute(): Result<List<Recipe>, String> {
        try {
            val all = when (val result = recipeRepository.findRecipes()) {
                is Result.Success -> result.data
                is Result.Error -> emptyList()
            }
            val saved = bookmarkRepository.getBookmarkedRecipeIds()

            val savedRecipes = all.filter { saved.contains(it.id) }
            return Result.Success(savedRecipes)
        } catch (e: Exception) {
            return Result.Error("Failed to get saved recipes: $e")
        }
    }
}