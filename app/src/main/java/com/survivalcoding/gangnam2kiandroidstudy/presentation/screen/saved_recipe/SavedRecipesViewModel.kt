package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedRecipesViewModel(
    private val recipeRepository: RecipeRepository,
    private val getSavedRecipesUseCase: GetSavedRecipesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SavedRecipesState())
    val state: StateFlow<SavedRecipesState> = _state.asStateFlow()

    init {
        println("MainViewModel init")
        viewModelScope.launch {
            loadRecipes()
        }
    }

    suspend fun loadRecipes() {
        when (val response = recipeRepository.findRecipes()) {
            is Result.Success -> _state.update {
                it.copy(savedRecipes = response.data)
            }

            is Result.Error -> println("에러 처리")
        }
    }

    suspend fun saveNewRecipe(id: Long) {
        when (val response = recipeRepository.findRecipe(id)) {
            is Result.Success -> _state.update { currentState ->
                // 이미 저장된 레시피면 그대로
                if (currentState.savedRecipes.any { it.id == id }) {
                    currentState
                } else {
                    currentState.copy(
                        savedRecipes = currentState.savedRecipes + response.data
                    )
                }
            }

            is Result.Error -> println("에러 처리")
        }
    }

    // 파괴될 때
    override fun onCleared() {
        println("MainViewModel cleared")
        super.onCleared()
    }
}