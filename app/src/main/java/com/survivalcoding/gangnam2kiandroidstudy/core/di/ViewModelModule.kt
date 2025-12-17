package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.HomeViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient.IngredientViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe.SavedRecipesViewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe.SearchRecipesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(recipeRepository = get()) }
    viewModel {
        SavedRecipesViewModel(
            recipeRepository = get(),
            getSavedRecipesUseCase = get(),
        )
    }
    viewModel {
        SearchRecipesViewModel(
            recipeRepository = get(),
        )
    }
    viewModel{
        IngredientViewModel(
            recipeRepository = get(),
            getRecipeProcedureUseCase = get(),
        )
    }
}