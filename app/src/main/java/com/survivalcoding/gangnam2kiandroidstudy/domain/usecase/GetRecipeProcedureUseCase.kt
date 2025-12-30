package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository

class GetRecipeProcedureUseCase(
    private val procedureRepository: ProcedureRepository,
) {
    suspend fun execute(recipeId: Long): Result<List<String>, String> {
        try {
            val result = procedureRepository.getProcedure(recipeId)
            return Result.Success(
                listOf(
                    "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?",
                    "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur? Tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?",
                    "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?",
                    "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?",
                )
            )
        } catch (e: Exception) {
            return Result.Error("Failed to get recipe's procedure: $e")
        }
    }
}
