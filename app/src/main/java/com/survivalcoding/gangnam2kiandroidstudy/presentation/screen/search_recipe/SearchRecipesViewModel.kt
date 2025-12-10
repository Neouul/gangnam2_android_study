package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe

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
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchRecipesViewModel(
    private val recipeRepository: RecipeRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow(SearchRecipesState())
    val state: StateFlow<SearchRecipesState> = _state.asStateFlow()

    // 검색어만 담는 flow
    private val searchTermFlow = MutableStateFlow("")

    init {
        println("MainViewModel init")
        loadRecipes()
        observeSearchTerm()
    }

    // 검색어 변화 구독 → debounce → 필터링 호출
    @OptIn(FlowPreview::class)
    private fun observeSearchTerm() {
        viewModelScope.launch {
            searchTermFlow
                .onEach {
                    // debounce 시작 시점에서 로딩 시작
                    _state.update { it.copy(isLoading = true) }
                }
                .debounce(500)
                .collectLatest { term ->
                    applySearchFilter(term)
                }
        }
    }

    // 실제 필터 로직
    private fun applySearchFilter(term: String) {
        // 로딩 시작
        _state.update { it.copy(isLoading = true) }

        val all = state.value.allRecipes
        val filtered = all
            .filter { it.name.contains(term, ignoreCase = true) }
            .sortedBy { it.name }

        // 로딩 종료 & 결과 업데이트
        _state.update {
            it.copy(
                filteredRecipes = filtered,
                isLoading = false
            )
        }
    }

    // 검색어 입력 시 호출되는 메서드
    fun updateSearchTerm(term: String) {
        _state.update { it.copy(searchTerm = term) }
        searchTermFlow.value = term
    }

    fun loadRecipes() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            when (val response = recipeRepository.findRecipes()) {
                is Result.Success -> _state.update { currentState ->
                    currentState.copy(
                        allRecipes = response.data,
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
                SearchRecipesViewModel(
                    recipeRepository = recipeRepository,
                    savedStateHandle = savedStateHandle,
                )
            }
        }
    }
}