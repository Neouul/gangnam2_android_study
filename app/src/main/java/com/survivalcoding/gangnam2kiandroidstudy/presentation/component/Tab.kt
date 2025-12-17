package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
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
fun Tab(
    labels: List<String>,
    selectedIndex: Int = 0,
    modifier: Modifier = Modifier,
    onValueChange: (Int) -> Unit = {},
) {
    Box(
        modifier = modifier
            .width(375.dp)
            .height(58.dp)
            .background(color = AppColors.white),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            val isSelected = selectedIndex == 0

            tabs2Label(
                text = labels[0],
                isSelected = isSelected,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onValueChange(0)
                    }
            )
            Spacer(modifier = Modifier.width(15.dp))
            tabs2Label(
                text = labels[1],
                isSelected = !isSelected,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onValueChange(1)
                    }
            )
        }
    }
}

@Composable
fun tabs2Label(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(33.dp)
            .background(
                color = if (isSelected) AppColors.primary100 else AppColors.white,
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = AppTextStyles.smallerTextBold.copy(color = AppColors.white),
            color = if (isSelected) AppColors.white else AppColors.primary80
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TabPreview() {
    Tab(listOf("Label1", "Label2"))
}