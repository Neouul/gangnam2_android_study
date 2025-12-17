package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeProcedureUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetRecipeProcedureUseCase(get()) }
    single { GetSavedRecipesUseCase(get(), get()) }
}