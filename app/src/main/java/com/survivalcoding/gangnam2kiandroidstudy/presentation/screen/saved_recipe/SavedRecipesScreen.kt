package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved_recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SavedRecipesScreen(
    modifier: Modifier = Modifier,
    viewModel: SavedRecipesViewModel = viewModel(factory = SavedRecipesViewModel.Factory)
) {
    val savedRecipes by viewModel.savedRecipes.collectAsState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Saved recipes",
            style = AppTextStyles.mediumTextBold,
            modifier = Modifier
                .padding(top = 54.dp, bottom = 20.dp)
        )

        LazyColumn(
            modifier = Modifier.padding(horizontal = 30.dp),
        ) {
            items(savedRecipes) { recipe ->
                RecipeCard(
                    recipe = recipe,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewSavedRecipesScreen() {
    SavedRecipesScreen()
}