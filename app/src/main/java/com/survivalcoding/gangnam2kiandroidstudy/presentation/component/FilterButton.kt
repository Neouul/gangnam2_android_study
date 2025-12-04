package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun FilterButton(
    text: String,
    isSelected: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .height(28.dp)
            .background(
                color = if (isSelected) AppColors.primary100 else AppColors.white,
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = 1.dp,
                color = if (isSelected) AppColors.primary100 else AppColors.primary80,
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(
                    vertical = 5.dp,
                    horizontal = 10.dp,
                )
                .height(18.dp)
        ) {
            Text(
                text = text,
                style = AppTextStyles.smallerTextRegular,
                color = if (isSelected) AppColors.white else AppColors.primary80,
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewFilterButton() {
    Row {
        FilterButton("Text", false)
        Spacer(Modifier.width(16.dp))
        FilterButton("Text", true)
    }
}