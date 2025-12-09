package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.MockRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl

class AppApplication : Application() {

    // 싱글턴 객체
    val recipeDataSource by lazy { MockRecipeDataSourceImpl() }
    val recipeRepository by lazy { RecipeRepositoryImpl(recipeDataSource) }

    override fun onCreate() {
        super.onCreate()
    }
}