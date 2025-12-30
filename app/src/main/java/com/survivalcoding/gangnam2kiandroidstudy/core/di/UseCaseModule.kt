package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.CopyLinkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeProcedureUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetSavedRecipesUseCase(get(), get()) }
    single { CopyLinkUseCase(get()) }
    single { GetRecipesUseCase(get()) }
    single { GetRecipeDetailsUseCase(get()) }
    single { GetRecipeProcedureUseCase(get()) }
}