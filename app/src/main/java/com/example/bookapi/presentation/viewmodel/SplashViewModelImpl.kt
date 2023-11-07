package com.example.bookapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapi.domain.navigation.Navigator
import com.example.bookapi.domain.repository.AuthRepository
import com.example.bookapi.presentation.splash.SplashFragmentDirections
import com.example.bookapi.presentation.splash.vm.SplashViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val repository: AuthRepository,
    private val navigator: Navigator
): SplashViewModel, ViewModel() {

    override val isFirstFlow: Flow<Boolean> =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)

    init {
        viewModelScope.launch {
            if (repository.isFirst()) {
                navigator.navigateTo(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
            } else{
                navigator.navigateTo(SplashFragmentDirections.actionSplashFragmentToBaseFragment2())
            }

        }
    }
}