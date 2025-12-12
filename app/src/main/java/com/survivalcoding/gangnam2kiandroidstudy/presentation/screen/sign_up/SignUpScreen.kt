package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_up

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.CustomCheckbox
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.CustomDivider
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.InputField
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SocialButton
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,

    name: String,
    email: String,
    password: String,
    confirmPassword: String,
    isChecked: Boolean,

    onNameChange: (String) -> Unit = {},
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onConfirmPasswordChange: (String) -> Unit = {},
    onCheckedChange: (Boolean) -> Unit = {},

    onSignUpClick: () -> Unit = {},
    onGoogleSignInClick: () -> Unit = {},
    onFacebookSignInClick: () -> Unit = {},
    onSignInNavigateClick: () -> Unit = {},
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
            // 회원가입 텍스트
            Column(
                modifier = Modifier
                    .align(Alignment.Start),
            ) {
                Text(
                    text = "Create an account",
                    style = AppTextStyles.largeTextBold,
                )
                Text(
                    text = "Let’s help you set up your account, it won’t take long.",
                    style = AppTextStyles.smallerTextRegular,
                    modifier = Modifier
                        .width(195.dp)
                        .padding(top = 5.dp)
                )
            }

            // 이름 입력
            InputField(
                label = "Name",
                placeholder = "Enter Name",
                value = name,
                modifier = Modifier
                    .padding(vertical = 20.dp),
                onValueChange = onNameChange,
            )

            // 이메일 입력
            InputField(
                label = "Email",
                placeholder = "Enter Email",
                value = email,
                onValueChange = onEmailChange,
            )

            // 비밀번호 입력
            InputField(
                label = "Password",
                placeholder = "Enter Password",
                value = password,
                modifier = Modifier
                    .padding(vertical = 20.dp),
                onValueChange = onPasswordChange,
                isPassword = true,
            )

            // 비밀번호 확인
            InputField(
                label = "Confirm Password",
                placeholder = "Retype Password",
                value = confirmPassword,
                onValueChange = onConfirmPasswordChange,
                isPassword = true,
            )

            // 이용약관 동의
            Row(
                modifier = Modifier
                    .padding(
                        top = 20.dp,
                        start = 10.dp,
                        bottom = 26.dp,
                    )
                    .height(17.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomCheckbox(
                    isChecked = isChecked,
                    onCheckedChange = onCheckedChange
                )
                Spacer(Modifier.width(5.dp))
                Text(
                    text = "Accept terms & Condition",
                    style = AppTextStyles.smallerTextRegular,
                    color = AppColors.secondary100,
                )
            }

            // Sign Up 버튼
            BigButton(
                text = "Sign Up",
                modifier = Modifier
                    .clickable { onSignUpClick() }
            )

            // Or Sign in With
            Row(
                modifier = Modifier
                    .padding(
                        top = 14.dp,
                        bottom = 20.dp,
                    )
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
                    .padding(bottom = 20.dp)
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
                    .align(Alignment.CenterHorizontally),
            ) {
                Text(
                    text = "Already a member? ",
                    style = AppTextStyles.smallerTextSemiBold,
                    color = AppColors.black,
                )
                Text(
                    text = "Sign In",
                    style = AppTextStyles.smallerTextSemiBold,
                    color = AppColors.secondary100,
                    modifier = Modifier.clickable { onSignInNavigateClick() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSignUp() {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val isChecked = remember { mutableStateOf(false) }

    SignUpScreen(
        name = name.value,
        email = email.value,
        password = password.value,
        confirmPassword = confirmPassword.value,
        isChecked = isChecked.value,

        onNameChange = { name.value = it },
        onEmailChange = { email.value = it },
        onPasswordChange = { password.value = it },
        onConfirmPasswordChange = { confirmPassword.value = it },
        onCheckedChange = { isChecked.value = it },
    )
}
