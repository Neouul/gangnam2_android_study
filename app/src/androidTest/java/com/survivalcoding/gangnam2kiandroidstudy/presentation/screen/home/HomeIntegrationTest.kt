package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FakeRecipeRepository
import org.junit.Rule
import org.junit.Test

class HomeIntegrationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadRecipes_success_showsData() {
        val fakeRepository = FakeRecipeRepository()
        val viewModel = HomeViewModel(fakeRepository)

        composeTestRule.setContent {
            HomeRoot(
                onNavigateToSearch = {},
                onNavigateToProfile = {},
                onNavigateToRecipeDetail = {},
                viewModel = viewModel
            )
        }

        // Check if data is displayed
        composeTestRule.onNodeWithText("Hello Jega").assertIsDisplayed()
        composeTestRule.onAllNodesWithText("Test Recipe 1").onFirst().assertIsDisplayed()
    }

    @Test
    fun loadRecipes_error_showsErrorState() {
        val fakeRepository = FakeRecipeRepository()
        fakeRepository.shouldReturnError = true
        val viewModel = HomeViewModel(fakeRepository)

        composeTestRule.setContent {
            HomeRoot(
                onNavigateToSearch = {},
                onNavigateToProfile = {},
                onNavigateToRecipeDetail = {},
                viewModel = viewModel
            )
        }

        // Check if error message is displayed
        composeTestRule.onNodeWithText("에러가 발생했습니다").assertIsDisplayed()
    }
}
