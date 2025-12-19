package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

sealed interface SplashEvent {
    data object NavigateToSignIn : SplashEvent
    data object SnackBarNetworkDisconnected : SplashEvent
    data object SnackBarNetworkConnected : SplashEvent
}