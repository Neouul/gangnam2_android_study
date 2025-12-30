package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.toFormatString
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchRecipesViewModelTest {

    private lateinit var viewModel: SearchRecipesViewModel
    private val getRecipesUseCase: GetRecipesUseCase = mockk()
    private val testDispatcher = StandardTestDispatcher()

    private val sampleRecipes = listOf(
        Recipe(
            id = 1,
            category = RecipeCategory.INDIAN,
            name = "Traditional Indian Curry",
            imageUrl = "",
            chef = "Chef A",
            time = "30 mins",
            rating = 4.5,
            ingredients = emptyList()
        ),
        Recipe(
            id = 2,
            category = RecipeCategory.ITALIAN,
            name = "Pasta Carbonara",
            imageUrl = "",
            chef = "Chef B",
            time = "20 mins",
            rating = 4.0,
            ingredients = emptyList()
        ),
        Recipe(
            id = 3,
            category = RecipeCategory.ASIAN,
            name = "Sushi Roll",
            imageUrl = "",
            chef = "Chef C",
            time = "45 mins",
            rating = 5.0,
            ingredients = emptyList()
        )
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        coEvery { getRecipesUseCase.execute() } returns Result.Success(sampleRecipes)
        viewModel = SearchRecipesViewModel(getRecipesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Initial State Validation`() = runTest {
        // Override mock to simulate delay for loading state verification
        coEvery { getRecipesUseCase.execute() } coAnswers {
            delay(100)
            Result.Success(emptyList())
        }
        val initialStateViewModel = SearchRecipesViewModel(getRecipesUseCase)
        
        // Before advancing, it should be in default state (isLoading=false)
        assertFalse(initialStateViewModel.state.value.isLoading)
        assertTrue(initialStateViewModel.state.value.allRecipes.isEmpty())
        
        // Advance to start the loadRecipes launch, but pause at delay
        testDispatcher.scheduler.runCurrent()
        assertTrue("isLoading should be true while repository is fetching", initialStateViewModel.state.value.isLoading)

        // Advance time to finish loading
        advanceTimeBy(100)
        testDispatcher.scheduler.runCurrent()
        assertFalse("isLoading should be false after fetching", initialStateViewModel.state.value.isLoading)
    }

    @Test
    fun `Successful Recipe Loading`() = runTest {
        advanceTimeBy(1) // let init's loadRecipes complete
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(sampleRecipes, viewModel.state.value.allRecipes)
        assertFalse(viewModel.state.value.isLoading)
    }

    @Test
    fun `Failed Recipe Loading`() = runTest {
        coEvery { getRecipesUseCase.execute() } returns Result.Error("Error")
        val failViewModel = SearchRecipesViewModel(getRecipesUseCase)

        testDispatcher.scheduler.advanceUntilIdle()

        assertFalse(failViewModel.state.value.isLoading)
        assertTrue(failViewModel.state.value.allRecipes.isEmpty())
    }

    @Test
    fun `Empty Recipe List from Repository`() = runTest {
        coEvery { getRecipesUseCase.execute() } returns Result.Success(emptyList())
        val emptyViewModel = SearchRecipesViewModel(getRecipesUseCase)

        testDispatcher.scheduler.advanceUntilIdle()

        assertFalse(emptyViewModel.state.value.isLoading)
        assertTrue(emptyViewModel.state.value.allRecipes.isEmpty())
        assertTrue(emptyViewModel.state.value.filteredRecipes.isEmpty())
    }

    @Test
    fun `Back Navigation Event`() = runTest {
        val events = mutableListOf<SearchRecipesEvent>()
        val job = launch {
            viewModel.event.toList(events)
        }

        viewModel.onAction(SearchRecipesAction.OnBackClick)
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(SearchRecipesEvent.NavigateToBack, events.first())
        job.cancel()
    }

    @Test
    fun `Recipe Card Click Navigation`() = runTest {
        val events = mutableListOf<SearchRecipesEvent>()
        val job = launch {
            viewModel.event.toList(events)
        }

        val recipeId = 1L
        viewModel.onAction(SearchRecipesAction.OnRecipeCardClick(recipeId))
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(SearchRecipesEvent.NavigateToRecipeDetail(recipeId), events.first())
        job.cancel()
    }

    @Test
    fun `Search Term Update with Debounce`() = runTest {
        testDispatcher.scheduler.advanceUntilIdle() // complete initial loading
        assertEquals(3, viewModel.state.value.filteredRecipes.size)

        viewModel.onAction(SearchRecipesAction.OnSearchTermChange("Pasta"))
        testDispatcher.scheduler.runCurrent()
        assertEquals("Pasta", viewModel.state.value.searchTerm)
        assertTrue(viewModel.state.value.isLoading)

        // Debounce is 500ms
        advanceTimeBy(499)
        testDispatcher.scheduler.runCurrent()
        assertTrue(viewModel.state.value.isLoading)
        assertEquals(3, viewModel.state.value.filteredRecipes.size) // Still shows previous list

        advanceTimeBy(1)
        testDispatcher.scheduler.advanceUntilIdle()
        assertFalse(viewModel.state.value.isLoading)
        assertEquals(1, viewModel.state.value.filteredRecipes.size)
        assertEquals("Pasta Carbonara", viewModel.state.value.filteredRecipes[0].name)
    }

    @Test
    fun `Loading State during Search Debounce`() = runTest {
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.onAction(SearchRecipesAction.OnSearchTermChange("Curry"))
        testDispatcher.scheduler.runCurrent() // Allow onEach to run
        assertTrue(viewModel.state.value.isLoading)

        advanceTimeBy(501)
        testDispatcher.scheduler.advanceUntilIdle()
        assertFalse(viewModel.state.value.isLoading)
    }

    @Test
    fun `Basic Search Filtering`() = runTest {
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.onAction(SearchRecipesAction.OnSearchTermChange("Traditional"))
        advanceTimeBy(501)
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(1, viewModel.state.value.filteredRecipes.size)
        assertTrue(viewModel.state.value.filteredRecipes.all { it.name.contains("Traditional", ignoreCase = true) })
    }

    @Test
    fun `Search with No Matching Results`() = runTest {
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.onAction(SearchRecipesAction.OnSearchTermChange("NonExistent"))
        advanceTimeBy(501)
        testDispatcher.scheduler.advanceUntilIdle()

        assertTrue(viewModel.state.value.filteredRecipes.isEmpty())
    }

    @Test
    fun `Search with Special Characters or Symbols`() = runTest {
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.onAction(SearchRecipesAction.OnSearchTermChange("@#$"))
        advanceTimeBy(501)
        testDispatcher.scheduler.advanceUntilIdle()

        assertTrue(viewModel.state.value.filteredRecipes.isEmpty())
    }

    @Test
    fun `Clearing the Search Term`() = runTest {
        testDispatcher.scheduler.advanceUntilIdle()

        // Search first
        viewModel.onAction(SearchRecipesAction.OnSearchTermChange("Pasta"))
        advanceTimeBy(501)
        testDispatcher.scheduler.advanceUntilIdle()
        assertEquals(1, viewModel.state.value.filteredRecipes.size)

        // Clear
        viewModel.onAction(SearchRecipesAction.OnSearchTermChange(""))
        advanceTimeBy(501)
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(3, viewModel.state.value.filteredRecipes.size)
        assertEquals("Pasta Carbonara", viewModel.state.value.filteredRecipes[0].name)
        assertEquals("Sushi Roll", viewModel.state.value.filteredRecipes[1].name)
        assertEquals("Traditional Indian Curry", viewModel.state.value.filteredRecipes[2].name)
    }

    @Test
    fun `Apply Filter Event and State Update`() = runTest {
        testDispatcher.scheduler.advanceUntilIdle()

        val events = mutableListOf<SearchRecipesEvent>()
        val job = launch {
            viewModel.event.toList(events)
        }

        val filter = FilterSearchState(rating = 5)
        viewModel.onFilterAction(FilterSearchAction.OnApplyFilterClick(filter))
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(filter, viewModel.state.value.filterSearchState)
        assertEquals(SearchRecipesEvent.SnackBarApplyFilter, events.first())
        assertEquals(1, viewModel.state.value.filteredRecipes.size)
        assertEquals(5.0, viewModel.state.value.filteredRecipes[0].rating, 0.0)

        job.cancel()
    }

    @Test
    fun `Dismiss Filter Event`() = runTest {
        testDispatcher.scheduler.advanceUntilIdle()

        val events = mutableListOf<SearchRecipesEvent>()
        val job = launch {
            viewModel.event.toList(events)
        }

        viewModel.onFilterAction(FilterSearchAction.OnDismissFilter)
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(SearchRecipesEvent.SnackBarCancelFilter, events.first())
        job.cancel()
    }

    @Test
    fun `Filtering by Time - Newest`() = runTest {
        testDispatcher.scheduler.advanceUntilIdle()

        val filter = FilterSearchState(time = "Newest")
        viewModel.onFilterAction(FilterSearchAction.OnApplyFilterClick(filter))
        testDispatcher.scheduler.advanceUntilIdle()

        // Sorted by id descending: 3, 2, 1
        assertEquals(3L, viewModel.state.value.filteredRecipes[0].id)
        assertEquals(2L, viewModel.state.value.filteredRecipes[1].id)
        assertEquals(1L, viewModel.state.value.filteredRecipes[2].id)
    }

    @Test
    fun `Filtering by Time - Oldest`() = runTest {
        testDispatcher.scheduler.advanceUntilIdle()

        val filter = FilterSearchState(time = "Oldest")
        viewModel.onFilterAction(FilterSearchAction.OnApplyFilterClick(filter))
        testDispatcher.scheduler.advanceUntilIdle()

        // Sorted by id ascending: 1, 2, 3
        assertEquals(1L, viewModel.state.value.filteredRecipes[0].id)
        assertEquals(2L, viewModel.state.value.filteredRecipes[1].id)
        assertEquals(3L, viewModel.state.value.filteredRecipes[2].id)
    }

    @Test
    fun `Filtering by Time - Popularity`() = runTest {
        testDispatcher.scheduler.advanceUntilIdle()

        val filter = FilterSearchState(time = "Popularity")
        viewModel.onFilterAction(FilterSearchAction.OnApplyFilterClick(filter))
        testDispatcher.scheduler.advanceUntilIdle()

        // Sorted by rating descending: 5.0 (id 3), 4.5 (id 1), 4.0 (id 2)
        assertEquals(5.0, viewModel.state.value.filteredRecipes[0].rating, 0.0)
        assertEquals(4.5, viewModel.state.value.filteredRecipes[1].rating, 0.0)
        assertEquals(4.0, viewModel.state.value.filteredRecipes[2].rating, 0.0)
    }

    @Test
    fun `Filtering by Rating`() = runTest {
        testDispatcher.scheduler.advanceUntilIdle()

        val filter = FilterSearchState(rating = 4)
        viewModel.onFilterAction(FilterSearchAction.OnApplyFilterClick(filter))
        testDispatcher.scheduler.advanceUntilIdle()

        // Only recipes with integer rating 4: Pasta Carbonara (4.0)
        // Note: 4.5 might not be included if we check it.toInt() == 4
        // Traditional Indian Curry (4.5).toInt() is 4.
        // Let's check ViewModel logic: list.filter { it.rating.toInt() == filter.rating }
        // 4.5.toInt() is 4. 4.0.toInt() is 4.
        assertEquals(2, viewModel.state.value.filteredRecipes.size)
    }

    @Test
    fun `Filtering by Rating with No Matches`() = runTest {
        testDispatcher.scheduler.advanceUntilIdle()

        val filter = FilterSearchState(rating = 1)
        viewModel.onFilterAction(FilterSearchAction.OnApplyFilterClick(filter))
        testDispatcher.scheduler.advanceUntilIdle()

        assertTrue(viewModel.state.value.filteredRecipes.isEmpty())
    }

    @Test
    fun `Filtering with Rating set to Null`() = runTest {
        testDispatcher.scheduler.advanceUntilIdle()

        val filter = FilterSearchState(rating = null)
        viewModel.onFilterAction(FilterSearchAction.OnApplyFilterClick(filter))
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(3, viewModel.state.value.filteredRecipes.size)
    }

    @Test
    fun `Filtering by Single Category`() = runTest {
        testDispatcher.scheduler.advanceUntilIdle()

        val filter = FilterSearchState(categories = setOf(RecipeCategory.INDIAN.toFormatString()))
        viewModel.onFilterAction(FilterSearchAction.OnApplyFilterClick(filter))
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(1, viewModel.state.value.filteredRecipes.size)
        assertEquals(RecipeCategory.INDIAN, viewModel.state.value.filteredRecipes[0].category)
    }

    @Test
    fun `Filtering by Multiple Categories`() = runTest {
        testDispatcher.scheduler.advanceUntilIdle()

        val filter = FilterSearchState(categories = setOf(RecipeCategory.INDIAN.toFormatString(), RecipeCategory.ITALIAN.toFormatString()))
        viewModel.onFilterAction(FilterSearchAction.OnApplyFilterClick(filter))
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(2, viewModel.state.value.filteredRecipes.size)
        assertTrue(viewModel.state.value.filteredRecipes.any { it.category == RecipeCategory.INDIAN })
        assertTrue(viewModel.state.value.filteredRecipes.any { it.category == RecipeCategory.ITALIAN })
    }

    @Test
    fun `Filtering with 'All' Category Selected`() = runTest {
        testDispatcher.scheduler.advanceUntilIdle()

        val filter = FilterSearchState(categories = setOf("All", RecipeCategory.INDIAN.toFormatString()))
        viewModel.onFilterAction(FilterSearchAction.OnApplyFilterClick(filter))
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(3, viewModel.state.value.filteredRecipes.size)
    }

    @Test
    fun `Filtering with Empty Category List`() = runTest {
        testDispatcher.scheduler.advanceUntilIdle()

        val filter = FilterSearchState(categories = emptySet())
        viewModel.onFilterAction(FilterSearchAction.OnApplyFilterClick(filter))
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(3, viewModel.state.value.filteredRecipes.size)
    }

    @Test
    fun `Combined Search and Filter`() = runTest {
        testDispatcher.scheduler.advanceUntilIdle()

        // Search for "Traditional"
        viewModel.onAction(SearchRecipesAction.OnSearchTermChange("Traditional"))
        advanceTimeBy(501)
        testDispatcher.scheduler.advanceUntilIdle()

        // Apply filter that matches "Traditional Indian Curry"
        val filter = FilterSearchState(categories = setOf(RecipeCategory.INDIAN.toFormatString()))
        viewModel.onFilterAction(FilterSearchAction.OnApplyFilterClick(filter))
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(1, viewModel.state.value.filteredRecipes.size)
        assertEquals("Traditional Indian Curry", viewModel.state.value.filteredRecipes[0].name)

        // Apply filter that DOES NOT match "Traditional Indian Curry"
        val filter2 = FilterSearchState(categories = setOf(RecipeCategory.ITALIAN.toFormatString()))
        viewModel.onFilterAction(FilterSearchAction.OnApplyFilterClick(filter2))
        testDispatcher.scheduler.advanceUntilIdle()

        assertTrue(viewModel.state.value.filteredRecipes.isEmpty())
    }

    @Test
    fun `Combined Filter and then Search`() = runTest {
        testDispatcher.scheduler.advanceUntilIdle()

        // Apply filter for ITALIAN
        val filter = FilterSearchState(categories = setOf(RecipeCategory.ITALIAN.toFormatString()))
        viewModel.onFilterAction(FilterSearchAction.OnApplyFilterClick(filter))
        testDispatcher.scheduler.advanceUntilIdle()
        assertEquals(1, viewModel.state.value.filteredRecipes.size)
        assertEquals("Pasta Carbonara", viewModel.state.value.filteredRecipes[0].name)

        // Search for "Pasta"
        viewModel.onAction(SearchRecipesAction.OnSearchTermChange("Pasta"))
        advanceTimeBy(501)
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(1, viewModel.state.value.filteredRecipes.size)
        assertEquals("Pasta Carbonara", viewModel.state.value.filteredRecipes[0].name)

        // Search for "Curry" (should NOT match because of ITALIAN filter)
        viewModel.onAction(SearchRecipesAction.OnSearchTermChange("Curry"))
        advanceTimeBy(501)
        testDispatcher.scheduler.advanceUntilIdle()

        assertTrue(viewModel.state.value.filteredRecipes.isEmpty())
    }

    @Test
    fun `ViewModel onCleared`() = runTest {
        // onCleared is protected, so we can't call it directly if the test is not in the same package.
        // In Kotlin, tests in the same package CAN see protected members of classes in that package?
        // No, protected is only for subclasses.
        // However, we can use a subclass for testing if needed.
        
        class TestViewModel(useCase: GetRecipesUseCase) : SearchRecipesViewModel(useCase) {
            public override fun onCleared() {
                super.onCleared()
            }
        }
        
        val testViewModel = TestViewModel(getRecipesUseCase)
        testViewModel.onCleared()
        // No crash
    }
}
