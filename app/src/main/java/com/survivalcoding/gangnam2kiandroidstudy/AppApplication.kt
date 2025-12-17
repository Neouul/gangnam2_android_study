package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.MockRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeProcedureUseCase

class AppApplication : Application() {

    // 싱글턴 객체
    val recipeDataSource by lazy { MockRecipeDataSourceImpl() }
    val recipeRepository by lazy { RecipeRepositoryImpl(recipeDataSource) }
    val procedureRepository by lazy { ProcedureRepositoryImpl() }

    // UseCase
    val getRecipeProcedureUseCase by lazy { GetRecipeProcedureUseCase(procedureRepository) }

    override fun onCreate() {
        super.onCreate()
    }
}