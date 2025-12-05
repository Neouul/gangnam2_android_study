package com.survivalcoding.gangnam2kiandroidstudy.presentation.component


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors


@Composable
fun SocialButton(
    iconId: Int,
    modifier: Modifier = Modifier
) {
    // 1. 색상 #6969691A (회색 + 10% 불투명도)
    // Compose에서는 ARGB (Alpha, Red, Green, Blue) 순서로 색상을 지정
    // 1A (Hex)는 약 10%의 투명도에 해당
    val shadowColor = Color(0x2A696969)

    // 2. 그림자 깊이 설정 (CSS의 흐림/스프레드를 대신함)
    val shadowElevation = 10.dp

    // 3. Card와 유사한 모양 (둥근 모서리)
    val shape = RoundedCornerShape(10.dp) // Card와 비슷하게 둥근 모서리 적용

    Box(
        modifier = modifier
            .size(44.dp)
            .background(
                color = AppColors.white,
                shape = RoundedCornerShape(10.dp)
            )
            .shadow(
                elevation = shadowElevation,
                shape = shape,
                // 스팟 색상을 사용하여 그림자 색상과 투명도를 지정합니다.
                spotColor = shadowColor,
//                ambientColor = shadowColor,
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(iconId),
            modifier = Modifier.size(24.dp),
            contentDescription = "소셜미디어 로그인 아이콘",
            tint = Color.Unspecified
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSocialButton() {
    SocialButton(iconId = R.drawable.social_icons_google)
}