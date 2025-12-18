package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun NewRecipeCard(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    onNewRecipeClick: () -> Unit = {},
) {
    val horizontalPadding = 9.3
    val verticalPadding = 10

    Box(
        modifier = Modifier
            .height(139.dp)
            .padding(horizontal = 7.5.dp)
            .width(251.dp),
        contentAlignment = Alignment.Center
    ) {
        // 둥근 카드 그림자
        Box(
            modifier = Modifier
                .width(266.dp)
                .height(115.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (-10).dp)
                .shadow(
                    elevation = 15.dp,
                    shape = RoundedCornerShape(10.dp),
                    spotColor = Color(0x4A000000),
                    ambientColor = Color(0x4A000000),
                ),
        )

        Box(
            modifier = modifier
                .width(251.dp)
                .height(127.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (-5).dp)
                .clickable { onNewRecipeClick() }
        ) {
            // 둥근 카드
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(95.dp)
                    .background(
                        color = AppColors.white,
                        shape = RoundedCornerShape(10.dp),
                    )
                    .align(Alignment.BottomCenter)
                    .padding(
                        vertical = verticalPadding.dp,
                        horizontal = horizontalPadding.dp
                    )
            ) {
                // 이름, 별점
                Column {
                    Text(
                        text = recipe.name,
                        style = AppTextStyles.smallTextBold,
                        color = AppColors.gray1,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.width(140.dp)
                    )
                    Row(
                        modifier = Modifier.padding(top = 5.dp, end = 10.dp)
                    ) {
                        repeat(5) {
                            Icon(
                                painter = painterResource(R.drawable.bold_star),
                                contentDescription = "별점 아이콘",
                                modifier = Modifier
                                    .padding(end = 2.dp)
                                    .size(12.dp),
                                tint = AppColors.rating,
                            )
                        }
                    }
                }

                // 프로필
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(25.dp)
                        .align(Alignment.BottomStart),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    // 프로필 이미지
                    Box(
                        modifier = Modifier
                            .size(25.dp)
                            .background(
                                color = AppColors.gray4,
                                shape = CircleShape,
                            )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    // 유저 이름
                    Text(
                        text = "By UserName",
                        style = AppTextStyles.smallerTextRegular,
                        color = AppColors.gray3,
                    )
                }

                // 시간
                Row(
                    modifier = Modifier
                        .height(24.dp)
                        .align(Alignment.BottomEnd),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.outline_timer),
                        contentDescription = "타이머 아이콘",
                        modifier = Modifier
                            .size(17.dp),
                        tint = AppColors.gray3,
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        text = recipe.time,
                        style = AppTextStyles.smallerTextRegular,
                        color = AppColors.gray3,
                    )
                }
            }

            // 이미지 그림자
            Box(
                modifier = Modifier
                    .width(80.dp)
                    .height(86.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = (-horizontalPadding).dp, y = 8.dp)
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
                    .padding(horizontal = horizontalPadding.dp)
                    .width(80.dp)
                    .height(86.dp)
                    .clip(CircleShape)
                    .align(Alignment.TopEnd)
                    .background(color = AppColors.gray1)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewNewRecipeCard() {
    NewRecipeCard(
        recipe = Recipe(
            id = 5,
            category = RecipeCategory.BREAKFAST,
            name = "Grilled salmon with avocado salsa",
            imageUrl = "https://cdn.pixabay.com/photo/2014/11/05/15/57/salmon-518032_1280.jpg",
            chef = "Alice Johnson",
            time = "25 min",
            rating = 4.5,
            ingredients = listOf()
        )
    )
}