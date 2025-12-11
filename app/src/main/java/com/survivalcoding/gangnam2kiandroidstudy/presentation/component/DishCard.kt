package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun DishCard(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier.size(width = 150.dp, height = 231.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(
                )
                .height(176.dp)
                .background(
                    color = AppColors.gray4.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(12.dp),
                )
                .align(Alignment.BottomCenter)
                .padding(10.dp)
        ) {
            Text(
                text = recipe.name,
                style = AppTextStyles.smallTextBold.copy(
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                ),
                color = AppColors.gray1,
                modifier = Modifier.padding(top = 56.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            // 시간
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart),
            ) {
                Text(
                    text = "Time",
                    style = AppTextStyles.smallerTextRegular,
                    color = AppColors.gray3,
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = recipe.time.substringBefore("min") + "Mins",
                    style = AppTextStyles.smallerTextBold,
                    color = AppColors.gray1,
                )
            }

            // 북마크
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.BottomEnd)
                    .background(
                        color = AppColors.white,
                        shape = CircleShape,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(R.drawable.outline_bookmark_inactive),
                    contentDescription = "북마크 아이콘",
                    modifier = Modifier
                        .size(16.dp),
                    tint = AppColors.primary80,
                )
            }
        }

        // 이미지 그림자
        Box(
            modifier = Modifier
                .size(110.dp)
                .align(Alignment.TopCenter)
                .offset(y = 8.dp)
                .shadow(
                    elevation = 10.dp,
                    shape = CircleShape,
                    spotColor = Color(0x55202020),
                    ambientColor = Color(0x55202020),
                ),
        )

        // 이미지
        AsyncImage(
            model = recipe.imageUrl,
            contentDescription = "음식 이미지",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .align(Alignment.TopCenter)
        )

        // 별점
        Box(
            modifier = Modifier
                .padding(10.dp)
                .size(width = 45.dp, height = 23.dp)
                .align(Alignment.TopEnd)
                .offset(y = 38.dp)
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
                        .size(10.dp),
                    tint = AppColors.rating,
                )
                Spacer(Modifier.width(5.dp))
                Text(
                    text = recipe.rating.toString(),
                    style = AppTextStyles.smallerTextRegular,
                    color = AppColors.black,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDishCard() {
    DishCard(
        recipe = Recipe(
            id = 5,
            category = "American",
            name = "Grilled salmon with avocado salsa",
            imageUrl = "https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_1280.jpg",
            chef = "Alice Johnson",
            time = "25 min",
            rating = 4.5,
            ingredients = listOf()
        )
    )
}