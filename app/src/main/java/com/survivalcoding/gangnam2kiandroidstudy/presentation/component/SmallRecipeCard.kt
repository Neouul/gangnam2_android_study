package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SmallRecipeCard(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable {
                onClick()
            }
    ) {
        AsyncImage(
            model = recipe.imageUrl,
            contentDescription = "레시피 이미지",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0x00000000),
                            Color(0xFF000000),
                        )
                    ),
                    shape = RoundedCornerShape(10.dp)
                )
        )

        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
            ) {
            Text(
                text = recipe.name,
                style = AppTextStyles.smallerTextBold,
                color = AppColors.white,
                modifier = Modifier.padding(bottom = 3.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "By ${recipe.chef}",
                style = AppTextStyles.smallerTextSmallLabel,
                color = AppColors.gray3,
            )
        }

        Box(
            modifier = Modifier
                .padding(10.dp)
                .size(width = 37.dp, height = 16.dp)
                .align(Alignment.TopEnd)
                .background(
                    color = AppColors.secondary20,
                    shape = RoundedCornerShape(20.dp)
                ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(
                    painter = painterResource(R.drawable.bold_star),
                    contentDescription = "별점 아이콘",
                    modifier = Modifier
                        .size(8.dp),
                    tint = AppColors.rating,
                )
                Spacer(Modifier.width(3.dp))
                Text(
                    text = recipe.rating.toString(),
                    style = AppTextStyles.smallerTextSmallLabel,
                    color = AppColors.black,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSmallRecipeCard() {
    val recipe = Recipe(
        id = 2,
        category = "Asian",
        name = "Spice roasted chicken with flavored rice",
        imageUrl = "https://cdn.pixabay.com/photo/2018/12/04/16/49/tandoori-3856045_1280.jpg",
        chef = "Mark Kelvin",
        time = "20 min",
        rating = 4.0,
        ingredients = listOf()
    )

    SmallRecipeCard(recipe)
}