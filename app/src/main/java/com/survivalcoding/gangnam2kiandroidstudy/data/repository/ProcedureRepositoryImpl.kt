package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import javax.inject.Inject

class ProcedureRepositoryImpl @Inject constructor(

) : ProcedureRepository {
    override suspend fun getProcedure(recipeId: Long): List<String> {
        return emptyList()
    }
}