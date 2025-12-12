package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.DishCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCategorySelector
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SearchBar
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe.SearchRecipesState
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun HomeScreen(
    state: HomeState,

    onSearchClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onFilterClick: () -> Unit = {},
    onCategoryClick: (String) -> Unit = {},
    onBookmarkClick: (Long) -> Unit = {},
) {
    // 스크롤 상태 저장
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .background(color = AppColors.white)
    ) {
        // 상단 텍스트, 프로필
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 64.dp,
                    start = 30.dp,
                    bottom = 30.dp,
                    end = 30.dp,
                ),
        ) {
            Column(
            ) {
                Text(
                    text = "Hello Jega",
                    style = AppTextStyles.largeTextBold,
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "What are you cooking today?",
                    style = AppTextStyles.smallerTextRegular,
                    color = AppColors.gray3,
                )
            }

            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = "프로필 이미지",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterEnd)
                    .background(
                        color = AppColors.secondary40,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable { onProfileClick() },
            )
        }

        // 검색창
        SearchBar(
            modifier = Modifier
                .clickable { onSearchClick() }
                .padding(horizontal = 30.dp),
            state = SearchRecipesState(),
            onSearchTermChange = { },
        )

        // 카테고리 선택바
        RecipeCategorySelector(
            modifier = Modifier.padding(vertical = 15.dp),
            selectedCategory = state.selectedCategory,
            onCategoryClick = onCategoryClick,
        )

        // dish cards
        LazyRow() {
            item {
                Spacer(modifier = Modifier.width(30.dp))
            }

            items(state.selectedRecipes) { selected ->
                DishCard(
                    recipe = selected,
                    modifier = Modifier.padding(end = 15.dp),
                    isSaved = selected.id in state.savedRecipeIds,
                    onBookmarkClick = { onBookmarkClick(selected.id) },
                )
            }
        }

        // New Recipes
        Text(
            text = "New Recipes",
            style = AppTextStyles.normalTextBold,
            modifier = Modifier.padding(
                top = 20.dp,
                start = 30.dp,
                end = 5.dp,
            )
        )

        // New Recipe Cards

    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(
        state = HomeState(
            selectedRecipes = listOf(
                Recipe(
                    id = 2,
                    category = "Asian",
                    name = "Spice roasted chicken with flavored rice",
                    imageUrl = "https://cdn.pixabay.com/photo/2018/12/04/16/49/tandoori-3856045_1280.jpg",
                    chef = "Mark Kelvin",
                    time = "20 min",
                    rating = 4.0,
                    ingredients = listOf()
                ),
                Recipe(
                    id = 3,
                    category = "Chinese",
                    name = "Spicy fried rice mix chicken bali",
                    imageUrl = "https://cdn.pixabay.com/photo/2019/09/07/19/02/spanish-paella-4459519_1280.jpg",
                    chef = "Spicy Nelly",
                    time = "20 min",
                    rating = 4.0,
                    ingredients = listOf()
                ),
                Recipe(
                    id = 4,
                    category = "Japanese",
                    name = "Long Very Long Long Very Very Long Ttekbokki",
                    imageUrl = "https://cdn.pixabay.com/photo/2017/07/27/16/48/toppokki-2545943_1280.jpg",
                    chef = "Kim Dahee",
                    time = "30 min",
                    rating = 5.0,
                    ingredients = listOf()
                ),
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
        ),
    )
}