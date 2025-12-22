package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun MediumButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    isEnabled: Boolean = true,
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val isPressed by interactionSource.collectIsPressedAsState()

        Box(
            modifier = modifier
                .size(width = 243.dp, height = 54.dp)
                .background(
                    color = if (isPressed || !isEnabled) AppColors.gray4     // Disable
                    else AppColors.primary100,      // Default

                    shape = RoundedCornerShape(10.dp)
                )
                .clickable(
                    enabled = isEnabled,
                    interactionSource = interactionSource,
                    indication = null, // 시각적 ripple 효과를 원치 않으면 null
                    onClick = { onClick() }
                )
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Box(
                    modifier = Modifier.size(width = 114.dp, height = 24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = text,
                        style = AppTextStyles.normalTextBold.copy(color = AppColors.white)
                    )
                }
                Spacer(modifier = Modifier.width(9.dp))
                Icon(
                    painter = painterResource(R.drawable.outline_arrow_right),
                    modifier = Modifier.size(width = 20.dp, height = 20.dp),
                    tint = AppColors.white,
                    contentDescription = "오른쪽 화살표"
                )
            }
        }
}


@Preview(showBackground = true)
@Composable
fun MediumButtonPreview() {
    MediumButton("Button")
}