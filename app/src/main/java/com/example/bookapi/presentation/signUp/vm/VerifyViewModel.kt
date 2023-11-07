package com.example.bookapi.presentation.signUp.vm

import com.example.bookapi.data.remote.dto.auth.request.SignUpVerifyRequest
import kotlinx.coroutines.flow.Flow

interface VerifyViewModel {
    val isLoading: Flow<Boolean>
    val isConnecting: Flow<Boolean>
    val errorMsg: Flow<String>
    val message: Flow<Boolean>

    fun checkCode(signUpVerifyRequest: SignUpVerifyRequest)
    fun back()
}