package com.survivalcoding.gangnam2kiandroidstudy.domain.network

sealed interface NetworkEvent {
    object Connected : NetworkEvent
    object Disconnected : NetworkEvent
}
