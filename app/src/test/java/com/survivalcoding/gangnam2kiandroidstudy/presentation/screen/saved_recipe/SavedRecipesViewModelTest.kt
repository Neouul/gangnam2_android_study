package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe

import androidx.lifecycle.SavedStateHandle
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.MockRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
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
            savedStateHandle = SavedStateHandle(),
        )

        // when
        val expected = dataSource.getRecipes()
        viewModel.loadRecipes()
        // launch가 Main 디스패처에서 돌기 때문에 기다려줘야 함
        advanceUntilIdle()

        // then
        assertTrue(viewModel.savedRecipes.value.isNotEmpty())
        assertEquals(expected, viewModel.savedRecipes.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `saveNewRecipe 메서드 - 특정 id의 레시피를 state에 추가한다`() = runTest {
        // given: ViewModel의 init에서 모든 레시피를 불러오도록 되어있음
        // 특정 레시피 저장 확인을 위해 모킹
        val expected = Recipe(
            id = 2,
            category = "Asian",
            name = "Spice roasted chicken with flavored rice",
            imageUrl = "https://cdn.pixabay.com/photo/2018/12/04/16/49/tandoori-3856045_1280.jpg",
            chef = "Mark Kelvin",
            time = "20 min",
            rating = 4.0,
            ingredients = listOf(
                Ingredient(
                    id = 6,
                    name = "Chicken",
                    imageUrl = "https://cdn.pixabay.com/photo/2010/12/10/08/chicken-1140_1280.jpg",
                ) to 300,
                Ingredient(
                    id = 4,
                    name = "Rice",
                    imageUrl = "https://cdn.pixabay.com/photo/2016/02/29/05/46/brown-rice-1228099_1280.jpg",
                ) to 200,
            )
        )

        val mockRepository = mockk<RecipeRepository>()
        coEvery { mockRepository.findRecipes() } returns Result.Success(listOf())
        // findRecipe()도 반드시 mock 해야 함!
        coEvery { mockRepository.findRecipe(2) } returns Result.Success(expected)

        val viewModel: SavedRecipesViewModel = SavedRecipesViewModel(
            recipeRepository = mockRepository,
            savedStateHandle = SavedStateHandle(),
        )

        // when: id가 2인 레시피 추가하기
        viewModel.saveNewRecipe(2)

        // then
        assertTrue(viewModel.savedRecipes.value.isNotEmpty())
        assertEquals(listOf(expected), viewModel.savedRecipes.value)
    }

}