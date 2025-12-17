package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockBookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<BookmarkRepository> { MockBookmarkRepositoryImpl() }
    single<ProcedureRepository> { ProcedureRepositoryImpl() }
    single<RecipeRepository> { RecipeRepositoryImpl(get()) }
}