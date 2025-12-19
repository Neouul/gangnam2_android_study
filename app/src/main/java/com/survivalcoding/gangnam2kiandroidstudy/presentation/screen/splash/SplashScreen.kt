package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.MediumButton
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles


@Composable
fun SplashScreen(
    state: SplashState,
    onAction: (SplashAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        // 배경 이미지
        Image(
            painter = painterResource(R.drawable.splash_background),
            contentDescription = "Splash 배경 이미지",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

        // 그라이언트
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0x66000000),
                            Color(0xFF000000),
                        )
                    )
                )
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier.padding(
                    top = 104.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // 로고 이미지
                Image(
                    painter = painterResource(R.drawable.splash_logo),
                    contentDescription = "Splash 로고 이미지",
                    modifier = Modifier.size(79.dp),
                    contentScale = ContentScale.Crop,
                )

                Spacer(Modifier.height(14.dp))

                // 타이틀
                Text(
                    text = "100K+ Premium Recipe",
                    color = AppColors.white,
                    style = AppTextStyles.mediumTextBold.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
            }

            Spacer(Modifier.weight(1f))

            Column(
                modifier = Modifier.padding(
                    bottom = 64.dp,
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Get Cooking
                Text(
                    text = "Get Cooking",
                    color = AppColors.white,
                    style = AppTextStyles.smallerTextSemiBold.copy(
                        fontSize = 50.sp,
                        lineHeight = 60.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.width(213.dp)
                )

                Spacer(Modifier.height(20.dp))

                // Simple way to find Tasty Recipe
                Text(
                    text = "Simple way to find Tasty Recipe",
                    color = AppColors.white,
                    style = AppTextStyles.normalTextRegular,
                )
            }

            // 버튼
            MediumButton(
                text = "Start Cooking",
                modifier = Modifier
                    .padding(bottom = 84.dp),
                onClick = { onAction(SplashAction.OnStartClick) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSplashScreen() {
    SplashScreen(
        state = SplashState(),
        onAction = {},
    )
}