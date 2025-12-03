package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
    Box(
        modifier = modifier
            .size(width = 174.dp, height = 37.dp)
            .background(
                color = AppColors.primary100,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                onClick()
            }
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