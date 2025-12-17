package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun ProcedureItem(
    procedureIndex: Int = 0,
    procedureContext: String = "",
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(76.dp)
            .background(
                color = AppColors.gray4.copy(alpha = 0.5f),
                shape = RoundedCornerShape(12.dp)
            ),
    ) {
        Column(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 15.dp)
        ) {
            Text(
                text = "Step ${procedureIndex + 1}",
                style = AppTextStyles.smallerTextBold,
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = procedureContext,
                style = AppTextStyles.smallerTextRegular,
                color = AppColors.gray3,
            )
        }
    }
}