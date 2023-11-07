package com.example.bookapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapi.data.remote.dto.auth.request.SignUpVerifyRequest
import com.example.bookapi.domain.navigation.Navigator
import com.example.bookapi.domain.repository.AuthRepository
import com.example.bookapi.presentation.signUp.SignUpVerifyFragmentDirections
import com.example.bookapi.presentation.signUp.vm.VerifyViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyViewModelImpl @Inject constructor(
    private val repository: AuthRepository,
    private val navigator: Navigator
) : VerifyViewModel, ViewModel(){

    override val isLoading =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val isConnecting =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val errorMsg =
        MutableSharedFlow<String>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val message =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)


    override fun checkCode(signUpVerifyRequest: SignUpVerifyRequest) {
        viewModelScope.launch {
            repository.signUpVerify(signUpVerifyRequest)
            navigator.navigateTo(SignUpVerifyFragmentDirections.actionSignUpVerifyFragmentToLoginFragment())
        }
    }

    override fun back() {
        viewModelScope.launch {
            navigator.back()
        }
    }
}