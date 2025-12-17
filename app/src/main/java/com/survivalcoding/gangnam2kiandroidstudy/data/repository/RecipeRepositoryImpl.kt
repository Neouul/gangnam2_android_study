package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val dataSource: RecipeDataSource
) : RecipeRepository {

    override suspend fun findRecipe(id: Long): Result<Recipe, String> {
        try {
            return Result.Success(dataSource.getRecipe(id)!!.toModel())
        } catch (e: Exception) {
            return Result.Error("error : findRecipe($id) 실패")
        }
    }

    override suspend fun findRecipes(): Result<List<Recipe>, String> {
        try {
            return Result.Success(
                dataSource.getRecipes()
                    ?.filterNotNull()
                    ?.map { it.toModel() }
                    ?: emptyList())
        } catch (e: Exception) {
            return Result.Error("error : findRecipes() 실패")
        }
    }

}

