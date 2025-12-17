package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository

class ProcedureRepositoryImpl : ProcedureRepository {
    override suspend fun getProcedure(recipeId: Long): List<String> {
        return emptyList()
    }
}