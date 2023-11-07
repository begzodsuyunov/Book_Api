package com.example.bookapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapi.data.model.ResultData
import com.example.bookapi.data.remote.dto.auth.request.SignInRequest
import com.example.bookapi.domain.navigation.Navigator
import com.example.bookapi.domain.repository.AuthRepository
import com.example.bookapi.domain.usecase.BaseSignUseCase
import com.example.bookapi.domain.usecase.CheckPasswordUseCase
import com.example.bookapi.presentation.login.LoginFragmentDirections
import com.example.bookapi.presentation.login.vm.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor(
    private val navigator: Navigator,
    private val repository: AuthRepository,
    private val checkPasswordUseCase: CheckPasswordUseCase,
    private val numberUseCase: BaseSignUseCase,
)  : LoginViewModel, ViewModel() {

    override val isLoading =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val isConnecting =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val errorMsg =
        MutableSharedFlow<String>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val message =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)


    override fun openSignUpScreen() {
        viewModelScope.launch {
            navigator.navigateTo(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }
    }

    override fun openMainScreen(signInRequest: SignInRequest) {
        val checkPassword = checkPasswordUseCase.checkPassword(signInRequest.password)
        val checkNumber = numberUseCase.checkNumber(signInRequest.phone)
        viewModelScope.launch {
            if (checkPassword && checkNumber.isNotEmpty()) {
                repository.signIn(SignInRequest(checkNumber, signInRequest.password))
                    .collectLatest {
                        when (it) {
                            is ResultData.Fail -> errorMsg.emit(it.message)
                            is ResultData.HasConnection -> isConnecting.emit(it.hasConnection)
                            is ResultData.Loading -> isLoading.emit(it.isLoading)
                            is ResultData.Success -> navigator.navigateTo(LoginFragmentDirections.actionLoginFragmentToBaseFragment2())
                        }
                    }
                message.emit(true)
            } else message.emit(false)
        }

    }
}