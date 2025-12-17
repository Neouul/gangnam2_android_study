package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.IngredientItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Tab
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun IngredientScreen(
    state: IngredientState,
) {
    Column(
        modifier = Modifier.padding(horizontal = 30.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 54.dp)
                .height(24.dp)
        ) {

        }
        RecipeCard(
            modifier = Modifier.padding(vertical = 10.dp),
            recipe = state.recipe
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(41.dp)
        ) {
            Text(
                text = state.recipe.name,
                style = AppTextStyles.smallTextBold,
                modifier = Modifier.weight(1f)
            )
        }

        // 프로필

        Tab(
            labels = listOf("Ingredient", "Procedure")
        )

        Row(
            modifier = Modifier.height(24.dp)
        ) { }

        LazyColumn(

        ) {
            items(state.recipe.ingredients) { ingredientAmount ->
                IngredientItem(
                    ingredient = ingredientAmount.ingredient,
                    quantity = "${ingredientAmount.amount}g",
                    modifier = Modifier.padding(bottom = 10.dp),
                )
            }
        }
    }
}