package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.toCategory
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val recipeRepository: RecipeRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()


    init {
        println("MainViewModel init")
        loadRecipes()
    }

    // 카테고리 선택에 따라 선택된 레시피 리스트 업데이트
    fun onSelectCategory(category: String) {
        _state.update { it.copy(selectedCategory = category) }
        filterRecipesByCategory(category.toCategory())
    }

    private fun filterRecipesByCategory(category: RecipeCategory) {
        val all = state.value.allRecipes

        val filtered =
            if (category == RecipeCategory.ALL) {
                all
            } else if (category == RecipeCategory.NONE) {
                emptyList()
            } else {
                all.filter { recipe ->
                    recipe.category == category
                }
            }

        _state.update { it.copy(selectedRecipes = filtered) }
    }


    // 모든 레시피 읽어오기
    // race condition 방지
    private var loadJob: Job? = null
    fun loadRecipes() {
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            when (val response = recipeRepository.findRecipes()) {
                is Result.Success -> _state.update { currentState ->
                    val all = response.data

                    currentState.copy(
                        allRecipes = all,
                        selectedRecipes = if (currentState.selectedCategory.toCategory() == RecipeCategory.ALL) {
                            all
                        } else if (currentState.selectedCategory.toCategory() == RecipeCategory.NONE) {
                            emptyList()
                        } else {
                            all.filter { recipe ->
                                recipe.category == currentState.selectedCategory.toCategory()
                            }
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
}