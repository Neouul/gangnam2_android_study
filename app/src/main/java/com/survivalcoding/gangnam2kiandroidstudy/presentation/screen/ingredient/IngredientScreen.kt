package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.ingredient

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.IngredientAmount
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeCategory
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.IngredientItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.ProcedureItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SmallButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Tab
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun IngredientScreen(
    state: IngredientState,
    onBackClick: () -> Unit = {},
    onMoreClick: () -> Unit = {},
    onBookmarkClick: () -> Unit = {},
    onTapClick: (Int) -> Unit = {},
) {
    Column(
        modifier = Modifier.padding(horizontal = 30.dp)
    ) {
        // 뒤로가기, 더보기 바
        Row(
            modifier = Modifier
                .padding(top = 54.dp)
                .height(24.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.outline_arrow_right),
                contentDescription = "뒤로가기 버튼",
                modifier = Modifier
                    .size(20.dp)
                    .rotate(180f)
                    .clickable { onBackClick() }
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(R.drawable.outline_more),
                contentDescription = "더보기 아이콘"
            )
        }

        // 레시피카드
        RecipeCard(
            modifier = Modifier.padding(vertical = 10.dp),
            recipe = state.recipe,
            isDetail = true,
        )

        // 타이틀
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(41.dp)
                .padding(horizontal = 5.dp)
        ) {
            Text(
                text = state.recipe.name,
                style = AppTextStyles.smallTextBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f),
            )
            Spacer(modifier = Modifier.width(18.dp))
            Text(
                text = "(13k Reviews)",
                color = AppColors.gray3,
                style = AppTextStyles.smallTextRegular,
            )
        }

        // 프로필
        Row(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 8.dp)
                .fillMaxWidth()
                .height(40.dp)
        ) {
            // 프로필 이미지
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = AppColors.gray4,
                        shape = CircleShape,
                    )
            )

            // 유저 이름, 위치
            Column(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text(
                    text = "Laura wilson",
                    style = AppTextStyles.smallTextBold,
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row {
                    Icon(
                        painter = painterResource(R.drawable.bold_location),
                        contentDescription = "위치 아이콘",
                        tint = AppColors.primary80,
                        modifier = Modifier
                            .padding(end = 1.dp)
                            .size(17.dp),
                    )
                    Text(
                        text = "Lagos, Nigeria",
                        style = AppTextStyles.smallerTextRegular,
                        color = AppColors.gray3,
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // 팔로우 버튼
            SmallButton(
                text = "Follow",
                modifier = Modifier.width(85.dp),
                onClick = {},
            )
        }

        // 재료 / 과정 탭
        Tab(
            labels = listOf("Ingredient", "Procedure"),
            modifier = Modifier.fillMaxWidth(),
            selectedIndex = state.tapIndex,
            onValueChange = { onTapClick(it) }
        )

        // 몇인분, 재료 개수
        Row(
            modifier = Modifier
                .padding(top = 22.dp, bottom = 20.dp)
                .fillMaxWidth()
                .height(24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.outline_serve),
                contentDescription = "몇인분 아이콘",
                tint = AppColors.gray3,
            )
            Text(
                text = "1 serve",
                style = AppTextStyles.smallerTextRegular,
                color = AppColors.gray3,
                modifier = Modifier.padding(horizontal = 5.dp),
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = if (state.tapIndex == 0) "${state.recipe.ingredients.size} Items"
                else "${state.procedures.size} Items",
                style = AppTextStyles.smallerTextRegular,
                color = AppColors.gray3,
            )
        }

        LazyColumn {
            if (state.tapIndex == 0) {
                items(state.recipe.ingredients) { ingredientAmount ->
                    IngredientItem(
                        ingredient = ingredientAmount.ingredient,
                        quantity = "${ingredientAmount.amount}g",
                        modifier = Modifier.padding(bottom = 10.dp),
                    )
                }
            } else {
                items(state.procedures) { procedure ->
                    ProcedureItem(
                        procedureIndex = state.procedures.indexOf(procedure),
                        procedureContext = procedure,
                        modifier = Modifier.padding(bottom = 10.dp),
                    )
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewIngredientScreen() {
    IngredientScreen(
        state = IngredientState(
            recipe = Recipe(
                id = 1,
                category = RecipeCategory.ASIAN,
                name = "Spicy chicken burger with French fries",
                imageUrl = "",
                chef = "Laura wilson",
                time = "20 min",
                rating = 4.5,
                ingredients = List(6) {
                    IngredientAmount(
                        ingredient = Ingredient(
                            id = 1,
                            name = "Tomatos",
                            imageUrl = "",
                        ),
                        amount = 500,
                    )
                }
            )
        )
    )
}