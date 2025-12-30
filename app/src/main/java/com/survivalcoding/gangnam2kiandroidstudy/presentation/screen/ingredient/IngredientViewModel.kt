package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.CopyLinkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeProcedureUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class IngredientViewModel(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val getRecipeProcedureUseCase: GetRecipeProcedureUseCase,
    private val copyLinkUseCase: CopyLinkUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(IngredientState())
    val state: StateFlow<IngredientState> = _state.asStateFlow()

    init {

    }

    fun loadRecipeDetail(recipeId: Long) {
        viewModelScope.launch {
            when (val response = getRecipeDetailsUseCase.execute(recipeId)) {
                is Result.Success -> _state.update { it.copy(recipe = response.data) }

                is Result.Error -> println("에러 처리")
            }
        }
    }

    // tap 클릭마다 index 업데이트
    fun updateTabIndex(index: Int) {
        _state.update { it.copy(tabIndex = index) }
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

    fun copyLink(link: String) {
        copyLinkUseCase(link)
    }
}