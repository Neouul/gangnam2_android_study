package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe

import androidx.lifecycle.SavedStateHandle
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.MockRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SavedRecipesViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val dataSource: RecipeDataSource = MockRecipeDataSourceImpl()
    private val repository: RecipeRepository = RecipeRepositoryImpl(dataSource) // 가짜 리스트

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `loadRecipes 메서드 - 모든 레시피를 받아온다`() = runTest {
        // given
        val viewModel: SavedRecipesViewModel = SavedRecipesViewModel(
            recipeRepository = repository,
            getSavedRecipesUseCase = mockk(),
        )

        // when
        val expected = dataSource.getRecipes()?.filterNotNull()?.map { it.toModel() } ?: emptyList()
        viewModel.loadRecipes()
        // launch가 Main 디스패처에서 돌기 때문에 기다려줘야 함
        advanceUntilIdle()

        // then
        assertTrue(viewModel.state.value.savedRecipes.isNotEmpty())
        assertEquals(expected, viewModel.state.value.savedRecipes)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `saveNewRecipe 메서드 - 특정 id의 레시피를 state에 추가한다`() = runTest {
        // given: ViewModel의 init에서 모든 레시피를 불러오도록 되어있음
        // 특정 레시피 저장 확인을 위해 모킹
        val expected = Recipe(
            id = 2,
            category = RecipeCategory.ASIAN,
            name = "Spice roasted chicken with flavored rice",
            imageUrl = "https://cdn.pixabay.com/photo/2018/12/04/16/49/tandoori-3856045_1280.jpg",
            chef = "Mark Kelvin",
            time = "20 min",
            rating = 4.0,
            ingredients = listOf()
        )

        val mockRepository = mockk<RecipeRepository>()
        coEvery { mockRepository.findRecipes() } returns Result.Success(listOf())
        // findRecipe()도 반드시 mock 해야 함!
        coEvery { mockRepository.findRecipe(2) } returns Result.Success(expected)

        val viewModel: SavedRecipesViewModel = SavedRecipesViewModel(
            recipeRepository = mockRepository,
            getSavedRecipesUseCase = mockk(),
        )
        advanceUntilIdle()

        // when: id가 2인 레시피 추가하기
        viewModel.saveNewRecipe(2)
        advanceUntilIdle()

        // then
        assertTrue(viewModel.state.value.savedRecipes.isNotEmpty())
        assertEquals(listOf(expected), viewModel.state.value.savedRecipes)
    }

}