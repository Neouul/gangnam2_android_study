package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

sealed interface HomeAction {
    data object OnSearchClick : HomeAction
    data object OnProfileClick : HomeAction
    data class OnCategoryClick(val category: String) : HomeAction
    data class OnBookmarkClick(val recipeId: Long) : HomeAction
    data class OnDishClick(val recipeId: Long) : HomeAction
    data class OnNewRecipeClick(val recipeId: Long) : HomeAction
}