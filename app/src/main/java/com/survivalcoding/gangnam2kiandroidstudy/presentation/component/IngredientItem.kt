package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun IngredientItem(
    ingredient: Ingredient,
    quantity: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(76.dp)
            .background(
                color = AppColors.gray4.copy(alpha = 0.5f),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable {
                onClick()
            },
    ) {
        Row {
            AsyncImage(
                model = ingredient.imageUrl,
                contentDescription = "재료 이미지: ${ingredient.name}",
                modifier = Modifier
                    .padding(
                        start = 15.dp,
                        top = 12.dp,
                        end = 16.dp,
                        bottom = 12.dp,
                    )
                    .size(52.dp)
                    .clip(shape = RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = ingredient.name,
                style = AppTextStyles.normalTextBold,
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        top = 29.dp,
                        bottom = 23.dp,
                    ),
            )
            Text(
                text = quantity,
                style = AppTextStyles.smallTextRegular,
                color = AppColors.gray3,
                modifier = Modifier
                    .padding(
                        top = 28.dp,
                        bottom = 27.dp,
                        end = 15.dp
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewIngredientItem() {
    val ingredient = Ingredient(
        id = 1,
        name = "Tomatos",
        imageUrl = "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg",
    )

    IngredientItem(
        ingredient = ingredient,
        quantity = "500g"
    )
}