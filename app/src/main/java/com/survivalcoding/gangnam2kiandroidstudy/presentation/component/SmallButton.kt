package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
fun SmallButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val isPressed by interactionSource.collectIsPressedAsState()

    val backgroundColor = if (isPressed) {
        AppColors.gray4     // Disalbe
    } else {
        AppColors.primary100    // Default
    }

    Box(
        modifier = modifier
            .width(174.dp)
            .height(37.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(
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
                modifier = Modifier.size(width = 114.dp, height = 17.dp),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = text,
                    style = AppTextStyles.smallerTextBold.copy(color = AppColors.white)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SmallButtonPreview() {
    SmallButton("Button")
}

@Preview(showBackground = true)
@Composable
fun AllButtonPreview() {
    Column {
        BigButton("Button")
        Spacer(modifier = Modifier.height(16.dp))
        MediumButton("Button")
        Spacer(modifier = Modifier.height(16.dp))
        SmallButton("Button")
    }
}