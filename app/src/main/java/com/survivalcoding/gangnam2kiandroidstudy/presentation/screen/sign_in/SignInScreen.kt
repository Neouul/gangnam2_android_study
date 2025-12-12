package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_in

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.BigButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.CustomDivider
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.InputField
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SocialButton
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,

    email: String,
    password: String,
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},

    onSignInClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onGoogleSignInClick: () -> Unit = {},
    onFacebookSignInClick: () -> Unit = {},
    onSignUpNavigateClick: () -> Unit = {},
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = AppColors.white),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(30.dp)
        ) {
            // Hello
            Column(
                modifier = Modifier
                    .align(Alignment.Start),
            ) {
                Text(
                    text = "Hello,",
                    style = AppTextStyles.headerTextBold,
                )
                Text(
                    text = "Welcome Back!",
                    style = AppTextStyles.largeTextRegular,
                )
            }

            // 이메일 입력
            InputField(
                label = "Email",
                placeholder = "Enter Email",
                value = email,
                modifier = Modifier
                    .padding(
                        top = 27.dp,
                        bottom = 30.dp,
                    ),
                onValueChange = onEmailChange
            )

            // 비밀번호 입력
            InputField(
                label = "Enter Password",
                placeholder = "Enter Password",
                value = password,
                onValueChange = onPasswordChange,
                isPassword = true,
            )

            // 비밀번호 찾기
            Text(
                text = "Forgot Password?",
                style = AppTextStyles.smallerTextRegular,
                color = AppColors.secondary100,
                modifier = Modifier
                    .padding(
                        top = 20.dp,
                        start = 10.dp,
                        bottom = 25.dp,
                    )
                    .clickable {
                        onForgotPasswordClick()
                    }
            )

            // Sign In 버튼
            BigButton(
                text = "Sign In",
                onClick = onSignInClick
            )

            // Or Sign in With
            Row(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .size(width = 195.dp, height = 17.dp)
                    .align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomDivider()
                Text(
                    text = "Or Sign in With",
                    style = AppTextStyles.smallerTextSemiBold,
                    color = AppColors.gray4,
                    modifier = Modifier.padding(horizontal = 7.dp)
                )
                CustomDivider()
            }

            // 소셜 로그인
            Row(
                modifier = Modifier
                    .padding(bottom = 55.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                SocialButton(
                    iconId = R.drawable.social_icons_google,
                    modifier = Modifier.clickable { onGoogleSignInClick() }
                )
                Spacer(Modifier.width(25.dp))
                SocialButton(
                    iconId = R.drawable.social_icons_facebook,
                    modifier = Modifier.clickable { onFacebookSignInClick() }
                )
            }

            // 회원가입 하세요
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Don’t have an account? ",
                    style = AppTextStyles.smallerTextSemiBold,
                    color = AppColors.black
                )
                Text(
                    text = "Sign up",
                    style = AppTextStyles.smallerTextSemiBold,
                    color = AppColors.secondary100,
                    modifier = Modifier.clickable { onSignUpNavigateClick() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSignIn() {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    SignInScreen(
        email = email.value,
        password = password.value,

        onEmailChange = { email.value = it },
        onPasswordChange = { password.value = it },
    )
}