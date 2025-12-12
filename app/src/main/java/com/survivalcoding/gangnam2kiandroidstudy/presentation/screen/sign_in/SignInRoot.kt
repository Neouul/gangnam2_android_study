package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_in

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun SignInRoot(
    onSignInClick: () -> Unit,
    onSignUpNavigateClick: () -> Unit,
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    SignInScreen(
        email = email.value,
        password = password.value,
        onEmailChange = { email.value = it },
        onPasswordChange = { password.value = it },
        onSignInClick = onSignInClick,
        onSignUpNavigateClick = onSignUpNavigateClick,
    )
}