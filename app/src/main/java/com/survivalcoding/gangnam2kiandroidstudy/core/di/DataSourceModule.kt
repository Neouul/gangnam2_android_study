package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.MockRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<RecipeDataSource> { MockRecipeDataSourceImpl() }
}