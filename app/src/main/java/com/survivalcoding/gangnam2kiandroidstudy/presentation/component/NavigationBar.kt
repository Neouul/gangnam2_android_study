package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors

@Composable
fun NavigationBar(
    modifier: Modifier = Modifier,
    currentRoute: NavKey?,
    onItemClick: (Route) -> Unit,
    isSavedScreen: Boolean = false,
) {
    val colors = NavigationBarItemDefaults.colors(
        selectedIconColor = AppColors.primary100,
        unselectedIconColor = AppColors.gray4,
        indicatorColor = Transparent, // 선택 시 생기는 배경 제거
    )

    // 전체 높이를 벡터 이미지 높이(122dp)에 맞춤
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(122.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = AppColors.white)
                .align(Alignment.BottomCenter)
        )

        // 1. [가장 아래 계층] 배경 벡터 이미지
        if (!isSavedScreen) {
            Image(
                painter = painterResource(id = R.drawable.navbar),
                contentDescription = "네비게이션바 배경",
                modifier = Modifier
                    .padding(top = 14.dp)
                    .height(106.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillBounds // 너비에 맞춰 꽉 채움
            )
        }

        // 2. [중간 계층] 실제 네비게이션 버튼들
        // NavigationBar의 기본 배경과 그림자를 완전히 제거하여 벡터 이미지만 보이게 함
        NavigationBar(
            containerColor = if (!isSavedScreen) Transparent else AppColors.white,
            tonalElevation = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp) // 버튼들이 배치될 영역의 높이
                .offset(y = 24.dp)
        ) {
            Spacer(Modifier.weight(0.2f))

            NavigationBarItem(
                selected = currentRoute is Route.Home,
                onClick = { onItemClick(Route.Home) },
                icon = { Icon(painterResource(R.drawable.outline_home), "홈") },
                colors = colors
            )
            NavigationBarItem(
                selected = currentRoute is Route.SavedRecipes,
                onClick = { onItemClick(Route.SavedRecipes) },
                icon = { Icon(painterResource(R.drawable.outline_bookmark_inactive), "북마크") },
                colors = colors
            )

            // 중앙 FAB 공간 확보를 위한 투명 Spacer
            if (!isSavedScreen) {
                Spacer(Modifier.weight(1f))
            }

            NavigationBarItem(
                selected = currentRoute is Route.Notifications,
                onClick = { onItemClick(Route.Notifications) },
                icon = { Icon(painterResource(R.drawable.outline_notification_bing), "알림") },
                colors = colors
            )
            NavigationBarItem(
                selected = currentRoute is Route.Profile,
                onClick = { onItemClick(Route.Profile) },
                icon = { Icon(painterResource(R.drawable.outline_profile), "프로필") },
                colors = colors
            )

            Spacer(Modifier.weight(0.2f))
        }

        // 3. [가장 위 계층] 중앙 Floating Action Button
        if (!isSavedScreen) {
            FloatingActionButton(
                onClick = { /* 추가 동작 */ },
                modifier = Modifier
                    .align(Alignment.TopCenter) // Box의 상단 중앙에 배치
                    .size(48.dp),
                containerColor = AppColors.primary100,
                shape = CircleShape,
                elevation = FloatingActionButtonDefaults.elevation(4.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.outline_union),
                    contentDescription = "추가",
                    tint = AppColors.white,
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun PreviewNavigationBar() {
    val currentRoute = Route.SavedRecipes
    NavigationBar(
        currentRoute = currentRoute,
        onItemClick = {},
        isSavedScreen = true,
    )
}