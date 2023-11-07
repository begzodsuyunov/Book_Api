package com.example.bookapi.presentation.login.vm

import com.example.bookapi.data.remote.dto.auth.request.SignInRequest
import kotlinx.coroutines.flow.Flow

interface LoginViewModel {

    val isLoading: Flow<Boolean>
    val isConnecting: Flow<Boolean>
    val errorMsg: Flow<String>
    val message: Flow<Boolean>

    fun openSignUpScreen()

    fun openMainScreen(signInRequest: SignInRequest)

}