package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles


@Composable
fun RecipeCategorySelector(
    modifier: Modifier = Modifier,
    initialSelect: String = "All",
    onCategoryClick: (String) -> Unit = {},
) {

    var selectedState by remember { mutableStateOf(initialSelect) }

    val categoryList = listOf(
        "All",
        "Indian",
        "Italian",
        "Asian",
        "Chinese",
        "Fruit",
        "Vegetables",
        "Protein",
        "Cereal",
        "Local Dishes",
    )

    LazyRow(
        modifier = modifier
            .height(51.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        item {
            Spacer(modifier = Modifier.width(30.dp))
        }

        items(categoryList) { category ->
            Box(
                modifier = Modifier.clickable {
                    selectedState = category
                    onCategoryClick(category)
                }
            ) {
                val isSelected = selectedState == category

                Text(
                    text = category,
                    style = AppTextStyles.smallerTextBold,
                    color = if (isSelected) AppColors.white else AppColors.primary80,
                    modifier = Modifier
                        .background(
                            color = if (isSelected) AppColors.primary100 else AppColors.white,
                            shape = if (isSelected) RoundedCornerShape(10.dp) else RectangleShape,
                        )
                        .padding(
                            vertical = 7.dp,
                            horizontal = 20.dp,
                        )
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewRecipeCategorySelector() {
    RecipeCategorySelector()
}