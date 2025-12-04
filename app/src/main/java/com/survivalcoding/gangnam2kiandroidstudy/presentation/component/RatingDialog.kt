package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RatingDialog(
    title: String,
    actionName: String,
    modifier: Modifier = Modifier,
    onChange: (Int) -> Unit = {},
    onDismiss: () -> Unit = {},
) {
    val rate = remember {
        mutableStateOf(0)
    }

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Box(
            modifier = modifier
                .size(width = 170.dp, height = 91.dp)
                .background(
                    color = AppColors.white,
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        vertical = 10.dp,
                        horizontal = 15.dp,
                    )
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    style = AppTextStyles.smallerTextRegular,
                    modifier = Modifier
                        .height(17.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(
                            vertical = 5.dp,
                        )
                        .height(24.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    repeat(5) { index ->
                        Icon(
                            painter = if (rate.value < index + 1) painterResource(R.drawable.outline_star)
                            else painterResource(R.drawable.bold_star),
                            contentDescription = "별점 아이콘",
                            modifier = Modifier
                                .size(18.dp)
                                .clickable {
                                    rate.value = index + 1
                                },
                            tint = AppColors.rating,
                        )
                        if (index < 4) {
                            Spacer(Modifier.width(10.dp))
                        }

                    }
                }

                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .background(
                            color = if (rate.value != 0) AppColors.rating else AppColors.gray4,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .clickable {
                            if (rate.value != 0) {
                                onChange(rate.value)
                                onDismiss()
                            }
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = actionName,
                        style = AppTextStyles.smallerTextSmallLabel,
                        color = AppColors.white,
                        modifier = Modifier
                            .padding(
                                vertical = 4.dp,
                                horizontal = 20.dp
                            )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewRatingDialog() {
    RatingDialog(
        title = "Rating",
        actionName = "Send",
        onChange = {
            print("$it 점을 매겼습니다!")
        }
    )
}