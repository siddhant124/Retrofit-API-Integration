package com.example.retrofitapiintegration.network

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observer(): Flow<Status>

    enum class Status {
        AVAILABLE,
        UNAVAILABLE,
        LOOSING,
        LOST
    }
}