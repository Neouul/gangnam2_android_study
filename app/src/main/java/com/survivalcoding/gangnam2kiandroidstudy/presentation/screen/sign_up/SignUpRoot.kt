package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.sign_up

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun SignUpRoot(
    onSignUpClick: () -> Unit,
    onSignInNavigateClick: () -> Unit,
) {
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

        onSignUpClick = onSignUpClick,
        onSignInNavigateClick = onSignInNavigateClick,
        onGoogleSignInClick = {},
        onFacebookSignInClick = {},
    )
}