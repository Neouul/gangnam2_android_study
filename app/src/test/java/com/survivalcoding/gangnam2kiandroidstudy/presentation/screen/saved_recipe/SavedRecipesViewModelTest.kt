package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SavedRecipesViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val getSavedRecipesUseCase: GetSavedRecipesUseCase = mockk()
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadRecipes 메서드 - 모든 레시피를 받아온다`() = runTest {
        // given
        val sampleRecipes = listOf(
            Recipe(
                id = 1,
                category = RecipeCategory.INDIAN,
                name = "Traditional Indian Curry",
                imageUrl = "",
                chef = "Chef A",
                time = "30 mins",
                rating = 4.5,
                ingredients = emptyList()
            )
        )
        coEvery { getSavedRecipesUseCase.execute() } returns Result.Success(sampleRecipes)

        val viewModel = SavedRecipesViewModel(
            getSavedRecipesUseCase = getSavedRecipesUseCase,
            getRecipeDetailsUseCase = getRecipeDetailsUseCase
        )

        // when
        // init block calls loadRecipes() which launches a coroutine.
        // We need to advance time to let it execute.
        testDispatcher.scheduler.advanceUntilIdle()

        // then
        assertTrue(viewModel.state.value.savedRecipes.isNotEmpty())
        assertEquals(sampleRecipes, viewModel.state.value.savedRecipes)
    }

    @Test
    fun `saveNewRecipe 메서드 - 특정 id의 레시피를 state에 추가한다`() = runTest {
        // given
        // Initial load returns empty list
        coEvery { getSavedRecipesUseCase.execute() } returns Result.Success(emptyList())

        val newRecipe = Recipe(
            id = 2,
            category = RecipeCategory.ASIAN,
            name = "Spice roasted chicken with flavored rice",
            imageUrl = "https://cdn.pixabay.com/photo/2018/12/04/16/49/tandoori-3856045_1280.jpg",
            chef = "Mark Kelvin",
            time = "20 min",
            rating = 4.0,
            ingredients = listOf()
        )
        coEvery { getRecipeDetailsUseCase.execute(2) } returns Result.Success(newRecipe)

        val viewModel = SavedRecipesViewModel(
            getSavedRecipesUseCase = getSavedRecipesUseCase,
            getRecipeDetailsUseCase = getRecipeDetailsUseCase
        )
        testDispatcher.scheduler.advanceUntilIdle() // Finish init load

        // when: id가 2인 레시피 추가하기
        viewModel.saveNewRecipe(2)
        testDispatcher.scheduler.advanceUntilIdle() // Finish saveNewRecipe

        // then
        assertTrue(viewModel.state.value.savedRecipes.isNotEmpty())
        assertEquals(listOf(newRecipe), viewModel.state.value.savedRecipes)
    }
}
