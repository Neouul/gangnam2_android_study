package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.core.coroutine.ApplicationScope
import com.survivalcoding.gangnam2kiandroidstudy.data.network.NetworkMonitorImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.network.NetworkMonitor
import org.koin.dsl.module

val networkModule = module {
    single<NetworkMonitor> { NetworkMonitorImpl(get(), get<ApplicationScope>()) }
}