package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SearchBar
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SmallRecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles


@Composable
fun SearchRecipesScreen(
    state: SearchRecipesState,

    onSearchTermChange: (String) -> Unit = {},
    onBackClick: () -> Unit = {},
    onFilterClick: () -> Unit = {},
) {

    Column(
        modifier = Modifier
            .background(color = AppColors.white)
            .padding(horizontal = 30.dp),
    ) {
        // Search recipes
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 54.dp,
                    bottom = 17.dp,
                )
        ) {
            Icon(
                painter = painterResource(R.drawable.outline_arrow_right),
                contentDescription = "뒤로가기 버튼",
                modifier = Modifier
                    .size(20.dp)
                    .rotate(180f)
                    .align(Alignment.CenterStart)
                    .clickable { onBackClick() }
            )

            Text(
                text = "Search recipes",
                style = AppTextStyles.mediumTextBold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // 검색란
        SearchBar(
            state = state,
            onSearchTermChange = onSearchTermChange,
            onFilterClick = onFilterClick,
        )

        // Search 타이틀
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ) {
            Text(
                text = if (state.searchTerm.isNotEmpty()) "Search Result" else "Recent Search",
                style = AppTextStyles.normalTextBold,
                modifier = Modifier.align(Alignment.CenterStart),
            )

            if (state.searchTerm.isNotEmpty()) {
                Text(
                    text = "${state.filteredRecipes.size} results",
                    style = AppTextStyles.smallerTextRegular,
                    color = AppColors.gray3,
                    modifier = Modifier.align(Alignment.CenterEnd),
                )
            }
        }

        if (state.isLoading) LoadingOverlay()
        else LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            if (state.searchTerm.isNotEmpty()) {
                items(state.filteredRecipes) { recipe ->
                    SmallRecipeCard(recipe = recipe)
                }
            } else {
                items(state.allRecipes) { recipe ->
                    SmallRecipeCard(recipe = recipe)
                }
            }
        }
    }
}

@Composable
fun LoadingOverlay() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .size(30.dp),
            strokeWidth = 4.dp,
            color = AppColors.gray3
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSearchRecipesScreen() {
    SearchRecipesScreen(
        state = SearchRecipesState(
            allRecipes = listOf(
                Recipe(
                    id = 5,
                    category = "American",
                    name = "Grilled salmon with avocado salsa",
                    imageUrl = "https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_1280.jpg",
                    chef = "Alice Johnson",
                    time = "25 min",
                    rating = 4.5,
                    ingredients = listOf()
                ),
            )
        )
    )
}