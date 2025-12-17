package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeProcedureUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe.SavedRecipesViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class IngredientViewModel(
    private val recipeRepository: RecipeRepository,
    private val getRecipeProcedureUseCase: GetRecipeProcedureUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(IngredientState())
    val state: StateFlow<IngredientState> = _state.asStateFlow()

    init {

    }

    fun loadRecipeDetail(recipeId: Long) {
        viewModelScope.launch {
            when (val response = recipeRepository.findRecipe(recipeId)) {
                is Result.Success -> _state.update { it.copy(recipe = response.data) }

                is Result.Error -> println("에러 처리")
            }
        }
    }

    // tap 클릭마다 index 업데이트
    fun updateTabIndex(index: Int) {
        _state.update { it.copy(tapIndex = index) }
    }

    // 과정 로드
    fun loadProcedure(recipeId: Long) {
        viewModelScope.launch {
            when (val result = getRecipeProcedureUseCase.execute(recipeId)) {
                is Result.Success -> _state.update { it.copy(procedures = result.data) }
                is Result.Error -> println("에러 처리")
            }
        }
    }

    companion object {
        fun factory(application: AppApplication): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    IngredientViewModel(
                        recipeRepository = application.recipeRepository,
                        getRecipeProcedureUseCase = application.getRecipeProcedureUseCase,
                    )
                }
            }
    }
}