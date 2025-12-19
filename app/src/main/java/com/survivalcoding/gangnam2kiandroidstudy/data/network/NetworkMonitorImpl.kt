package com.survivalcoding.gangnam2kiandroidstudy.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.survivalcoding.gangnam2kiandroidstudy.domain.network.NetworkEvent
import com.survivalcoding.gangnam2kiandroidstudy.domain.network.NetworkMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn

/**
 * NetworkMonitor (Singleton)의 역할
 *
 * ConnectivityManager를 감싼다
 * 전통적인 콜백 → Flow 로 변환
 * 앱 전역에서 공유 가능
 **/
class NetworkMonitorImpl(
    context: Context,
    scope: CoroutineScope,
) : NetworkMonitor {

    private val connectivityManager =
        context.getSystemService(ConnectivityManager::class.java)

    private val _events = MutableSharedFlow<NetworkEvent>(
        replay = 0,
        extraBufferCapacity = 1
    )
    override val events: SharedFlow<NetworkEvent> = _events

    override val isConnected: StateFlow<Boolean> =
        callbackFlow {
            // 👇 1. 전통적인 콜백
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    trySend(true)
                    _events.tryEmit(NetworkEvent.Connected)
                }

                override fun onLost(network: Network) {
                    trySend(false)
                    _events.tryEmit(NetworkEvent.Disconnected)
                }
            }

            // 👇 2. 콜백 등록
            val request = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()

            connectivityManager.registerNetworkCallback(request, callback)

            // 초기 상태
            val active = connectivityManager.activeNetwork
            val connected = active != null &&
                connectivityManager.getNetworkCapabilities(active)
                    ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true

            trySend(connected)

            // 👇 3. Flow 종료 시 정리
            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }
        // 👇 4. Flow → StateFlow (Singleton 공유)
        .distinctUntilChanged()
        .stateIn(
            scope,
            SharingStarted.WhileSubscribed(5_000),
            false,
        )
}
