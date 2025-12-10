package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.search_recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SmallRecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles


@Composable
fun SearchRecipesScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchRecipesViewModel = viewModel(factory = SearchRecipesViewModel.Factory),
    state: SearchRecipesState,

    onSearchTermChange: (String) -> Unit = { },

    onBackClick: () -> Unit = {},
) {

    Column(
        modifier = modifier.padding(horizontal = 30.dp),
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                value = state.searchTerm,
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = AppColors.white),
                placeholder = {
                    Text(
                        text = "Search recipe",
                        color = AppColors.gray4,
                        style = AppTextStyles.smallerTextRegular
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AppColors.gray4,
                    unfocusedBorderColor = AppColors.gray4,
                ),
                shape = RoundedCornerShape(10.dp),
                onValueChange = { viewModel.updateSearchTerm(it) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.outline_search_normal),
                        contentDescription = "검색 아이콘",
                        modifier = Modifier.size(20.dp),
                        tint = AppColors.gray4,
                    )
                },
            )

            Spacer(Modifier.width(20.dp))

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = AppColors.primary100)
            ) {
                Icon(
                    painter = painterResource(R.drawable.outline_setting_4),
                    contentDescription = "필터 아이콘",
                    tint = AppColors.white,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp)
                )
            }
        }

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

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            if (state.searchTerm.isNotEmpty()) {
//                viewModel.loadFilteredRecipes()
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