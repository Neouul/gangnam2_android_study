package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.data.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val recipeRepository: RecipeRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()


    init {
        println("MainViewModel init")
        loadRecipes()
    }

    fun onSelectCategory(category: String) {
        _state.update { it.copy(selectedCategory = category) }
        filterRecipesByCategory(category)
    }

    private fun filterRecipesByCategory(category: String) {
        val all = state.value.allRecipes

        val filtered =
            if (category == "All") {
                all
            } else {
                all.filter { recipe ->
                    recipe.category.equals(category, ignoreCase = true)
                }
            }

        _state.update { it.copy(selectedRecipes = filtered) }
    }


    fun loadRecipes() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            when (val response = recipeRepository.findRecipes()) {
                is Result.Success -> _state.update { currentState ->
                    val all = response.data

                    currentState.copy(
                        allRecipes = all,
                        selectedRecipes = if (currentState.selectedCategory == "All") all
                        else all.filter {
                            it.category.equals(
                                currentState.selectedCategory,
                                ignoreCase = true
                            )
                        },
                        isLoading = false,
                    )
                }

                is Result.Error -> {
                    println("에러 처리")
                    _state.update { it.copy(isLoading = false) }
                }
            }
        }
    }

    // 레시피 저장
    fun toggleBookmark(recipeId: Long) {
        _state.update { state ->
            val newBookmarks = if (recipeId in state.savedRecipeIds) {
                state.savedRecipeIds - recipeId  // 제거
            } else {
                state.savedRecipeIds + recipeId  // 추가
            }

            state.copy(savedRecipeIds = newBookmarks)
        }
    }

    // 파괴될 때
    override fun onCleared() {
        println("MainViewModel cleared")
        super.onCleared()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val recipeRepository =
                    (this[APPLICATION_KEY] as AppApplication).recipeRepository
                HomeViewModel(
                    recipeRepository = recipeRepository,
                    savedStateHandle = savedStateHandle,
                )
            }
        }
    }
}