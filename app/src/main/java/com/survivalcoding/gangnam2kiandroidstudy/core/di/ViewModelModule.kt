package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.HomeViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient.IngredientViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe.SavedRecipesViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe.SearchRecipesViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(getRecipesUseCase = get()) }
    viewModel {
        SavedRecipesViewModel(
            getSavedRecipesUseCase = get(),
            getRecipeDetailsUseCase = get(),
        )
    }
    viewModel {
        SearchRecipesViewModel(
            getRecipesUseCase = get(),
        )
    }
    viewModel {
        IngredientViewModel(
            getRecipeDetailsUseCase = get(),
            getRecipeProcedureUseCase = get(),
            copyLinkUseCase = get(),
        )
    }
    viewModel {
        SplashViewModel(get())
    }
}