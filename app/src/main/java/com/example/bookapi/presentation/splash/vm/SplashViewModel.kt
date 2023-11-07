package com.example.bookapi.presentation.splash.vm

import kotlinx.coroutines.flow.Flow

interface SplashViewModel {
    val isFirstFlow: Flow<Boolean>

}