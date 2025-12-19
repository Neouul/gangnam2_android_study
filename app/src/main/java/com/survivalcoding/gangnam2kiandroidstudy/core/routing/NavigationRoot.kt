package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home.HomeRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient.IngredientRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.main.MainScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe.SavedRecipesRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe.SearchRecipesRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_in.SignInRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_up.SignUpRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash.SplashScreen

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
) {
    val topLevelBackStack = rememberNavBackStack(Route.Splash)

    NavDisplay(
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        backStack = topLevelBackStack,
        entryProvider = entryProvider {
            entry<Route.Splash> {
                SplashScreen(
                    onClick = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignIn)
                    }
                )
            }
            entry<Route.SignIn> {
                SignInRoot(
                    onSignInClick = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.Main)
                    },
                    onSignUpNavigateClick = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignUp)
                    },
                )
            }
            entry<Route.SignUp> {
                SignUpRoot(
                    onSignUpClick = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.Main)
                    },
                    onSignInNavigateClick = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignIn)
                    },
                )
            }
            // Home, Saved, Notifications, Profile
            entry<Route.Main> {
                val mainBackStack = rememberNavBackStack(Route.Home)

                MainScreen(
                    backStack = mainBackStack,
                    body = {
                        NavDisplay(
                            modifier = modifier,
                            backStack = mainBackStack,
                            entryProvider = entryProvider {
                                entry<Route.Home> {
                                    HomeRoot(
                                        onNavigateToSearch = {
                                            topLevelBackStack.add(Route.SearchRecipes)
                                        },
                                        onNavigateToProfile = {
                                            mainBackStack.clear()
                                            mainBackStack.add(Route.Profile)
                                        },
                                        onNavigateToRecipeDetail = { recipeId ->
                                            topLevelBackStack.add(Route.RecipeDetail(recipeId))
                                        }
                                    )
                                }
                                entry<Route.SavedRecipes> {
                                    SavedRecipesRoot(
                                        onCardClick = { recipeId ->
                                            topLevelBackStack.add(Route.RecipeDetail(recipeId))
                                        },
                                    )
                                }
                                entry<Route.Notifications> {

                                }
                                entry<Route.Profile> {

                                }
                            }
                        )
                    }
                )
            }
            entry<Route.SearchRecipes> {
                SearchRecipesRoot(
                    onNavigateToBack = {
                        if (topLevelBackStack.size > 1) {
                            topLevelBackStack.removeAt(topLevelBackStack.lastIndex)
                        }
                    },
                    onNavigateToRecipeDetail = { recipeId ->
                        topLevelBackStack.add(Route.RecipeDetail(recipeId))
                    }
                )
            }
            entry<Route.RecipeDetail> { key ->
                IngredientRoot(
                    recipeId = key.recipeId,
                    onBackClick = {
                        if (topLevelBackStack.size > 1) {
                            topLevelBackStack.removeAt(topLevelBackStack.lastIndex)
                        }
                    }
                )
            }
        }
    )
}