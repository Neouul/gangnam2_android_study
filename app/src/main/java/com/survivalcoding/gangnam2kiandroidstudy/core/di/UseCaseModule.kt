package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeProcedureUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetRecipeProcedureUseCase(procedureRepository: ProcedureRepository): GetRecipeProcedureUseCase {
        return GetRecipeProcedureUseCase(procedureRepository)
    }

    @Provides
    fun provideGetSavedRecipesUseCase(
        bookmarkRepository: BookmarkRepository,
        recipeRepository: RecipeRepository,
    ): GetSavedRecipesUseCase {
        return GetSavedRecipesUseCase(bookmarkRepository, recipeRepository)
    }
}