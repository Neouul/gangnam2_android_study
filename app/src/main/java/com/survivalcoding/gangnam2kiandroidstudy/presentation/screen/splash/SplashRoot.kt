package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashRoot(
    onNavigateToSignIn: () -> Unit,
    viewModel: SplashViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event ->
            when (event) {
                SplashEvent.NavigateToSignIn -> onNavigateToSignIn()
                SplashEvent.SnackBarNetworkConnected -> {

                    snackbarHostState.showSnackbar(
                        message = "네트워크가 정상적으로 연결되었습니다.",
                        duration = SnackbarDuration.Short
                    )
                }

                SplashEvent.SnackBarNetworkDisconnected -> {

                    snackbarHostState.showSnackbar(
                        message = "네트워크가 연결되어 있지 않습니다.",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

    SplashScreen(
        state = state,
        onAction = { action -> viewModel.onAction(action) },
    )
}