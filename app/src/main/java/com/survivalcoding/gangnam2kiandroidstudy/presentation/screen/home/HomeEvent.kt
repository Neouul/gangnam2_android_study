package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

sealed interface HomeEvent {
    data object NavigateToSearch : HomeEvent
    data object NavigateToProfile : HomeEvent
    data class NavigateToRecipeDetail(val recipeId: Long) : HomeEvent
}
