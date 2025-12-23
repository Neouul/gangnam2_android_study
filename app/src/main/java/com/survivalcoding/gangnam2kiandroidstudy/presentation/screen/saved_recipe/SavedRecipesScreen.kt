package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import androidx.compose.ui.Alignment
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SavedRecipesScreen(
    state: SavedRecipesState,
    listState: LazyListState,
    modifier: Modifier = Modifier,

    onCardClick: (Long) -> Unit = {},
    onBookmarkClick: (Long) -> Unit = {},
) {

    Column(
        modifier = modifier.background(color = AppColors.white),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Saved recipes",
            style = AppTextStyles.mediumTextBold,
            modifier = Modifier
                .padding(top = 54.dp, bottom = 20.dp)
        )

        LazyColumn(
            state = listState,
            modifier = Modifier.padding(horizontal = 30.dp),
        ) {
            items(state.savedRecipes) { recipe ->
                RecipeCard(
                    recipe = recipe,
                    modifier = Modifier.padding(bottom = 20.dp),
                    onCardClick = { onCardClick(recipe.id) },
                    onBookmarkClick = { onBookmarkClick(recipe.id) },
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewSavedRecipesScreen() {
    SavedRecipesScreen(
        state = SavedRecipesState(),
        listState = LazyListState(),
    )
}