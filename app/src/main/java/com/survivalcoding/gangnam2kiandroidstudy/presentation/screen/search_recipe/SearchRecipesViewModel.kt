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
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
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

    @OptIn(FlowPreview::class)
    private fun observeSearchTerm() {
        searchTermFlow
            .debounce(300) // 300ms debounce
            .onEach { term -> filterRecipes(term) }
            .launchIn(viewModelScope)
    }

    private fun filterRecipes(term: String) {
        val allRecipes = state.value.allRecipes
        _state.update { currentState ->
            currentState.copy(
                filteredRecipes = allRecipes
                    .filter { it.name.contains(term, ignoreCase = true) }
                    .sortedBy { it.name }
            )
        }
    }

    fun updateSearchTerm(term: String) {
        _state.update { it.copy(searchTerm = term) }
        searchTermFlow.value = term
    }

    fun loadRecipes() {
        viewModelScope.launch {
            when (val response = recipeRepository.findRecipes()) {
                is Result.Success -> _state.update { currentState ->
                    currentState.copy(
                        allRecipes = response.data
                    )
                }

                is Result.Error -> println("에러 처리")
            }
        }
    }

    fun loadFilteredRecipes() {
        viewModelScope.launch {
            when (val response = recipeRepository.findRecipes()) {
                is Result.Success -> _state.update { currentState ->
                    currentState.copy(
                        filteredRecipes = response.data
                            .filter {
                                it.name.lowercase().contains(state.value.searchTerm.lowercase())
                            }
                            .sortedBy { it.name }
                    )
                }

                is Result.Error -> println("에러 처리")
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