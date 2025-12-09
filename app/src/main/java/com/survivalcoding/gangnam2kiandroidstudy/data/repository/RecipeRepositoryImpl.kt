package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

class RecipeRepositoryImpl(
    private val dataSource: RecipeDataSource
) : RecipeRepository {
    override suspend fun findRecipe(id: Long): Result<Recipe, String> {
        try {
            return Result.Success(dataSource.getRecipe(id))
        } catch (e: Exception) {
            return Result.Error("error : findRecipe($id) 실패")
        }
    }

    override suspend fun findRecipes(): Result<List<Recipe>, String> {
        try {
            return Result.Success(dataSource.getRecipes())
        } catch (e: Exception) {
            return Result.Error("error : findRecipes() 실패")
        }
    }

}

