package com.example.bookapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapi.data.model.ResultData
import com.example.bookapi.data.remote.dto.auth.request.SignUpRequest
import com.example.bookapi.domain.navigation.Navigator
import com.example.bookapi.domain.repository.AuthRepository
import com.example.bookapi.domain.usecase.BaseSignUseCase
import com.example.bookapi.domain.usecase.SignUpUseCase
import com.example.bookapi.presentation.signUp.SignUpFragmentDirections
import com.example.bookapi.presentation.signUp.vm.SignUpViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModelImpl @Inject constructor(
    private val repository: AuthRepository,
    private val useCase: SignUpUseCase,
    private val numberUseCase: BaseSignUseCase,
    private val navigator: Navigator
): SignUpViewModel, ViewModel() {

    override val isLoading =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val isConnecting =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val errorMsg =
        MutableSharedFlow<String>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val message =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)


    private fun register(signUpRequest: SignUpRequest) {
        viewModelScope.launch {
            repository.signUp(signUpRequest).collectLatest {
                when (it) {
                    is ResultData.Fail -> errorMsg.emit(it.message)
                    is ResultData.HasConnection -> isConnecting.emit(it.hasConnection)
                    is ResultData.Loading -> isLoading.emit(it.isLoading)
                    is ResultData.Success -> navigator.navigateTo(SignUpFragmentDirections.actionSignUpFragmentToSignUpVerifyFragment())
                }
            }
        }
    }

    override fun back() {
        viewModelScope.launch {
            navigator.back()
        }
    }

    override fun openVerifyScreen(
        firstName: String,
        lastName: String,
        number: String,
        password1: String,
        password2: String
    ) {
        viewModelScope.launch {
            if (useCase.checkName(firstName) && useCase.checkName(lastName) && useCase.checkSamePassword(
                    password1,
                    password2
                ) && numberUseCase.checkNumber(number).length > 10
            ) {
                message.emit(true)
                register(
                    SignUpRequest(
                        firstName = firstName,
                        lastName = lastName,
                        phone = numberUseCase.checkNumber(number),
                        password = password1
                    )
                )
            } else {
                message.emit(false)
            }
        }
    }
}