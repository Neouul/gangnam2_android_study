package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors

@Composable
fun MyDivider(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(50.dp)
            .height(1.dp)
            .background(
                color = AppColors.gray4,
                shape = RoundedCornerShape(2.dp)
            )
    )
}