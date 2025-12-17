package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

interface ProcedureRepository {
    suspend fun getProcedure(recipeId: Long): List<String>
}