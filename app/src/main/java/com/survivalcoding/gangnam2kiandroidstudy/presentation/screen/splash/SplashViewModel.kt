package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel() : ViewModel() {
    private val _state = MutableStateFlow(SplashState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<SplashEvent>()
    val event = _event.asSharedFlow()

    fun onAction(action: SplashAction) {
        when (action) {
            SplashAction.OnStartClick -> {
                viewModelScope.launch {
                    _event.emit(SplashEvent.NavigateToSignIn)
                }
            }
        }
    }
}