package com.survivalcoding.gangnam2kiandroidstudy.domain.network

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * StateFlow<Boolean> → 버튼 활성화용
 * SharedFlow<Event> → Snackbar용
 */
interface NetworkMonitor {
    val isConnected: StateFlow<Boolean>
    val events: SharedFlow<NetworkEvent>
}
