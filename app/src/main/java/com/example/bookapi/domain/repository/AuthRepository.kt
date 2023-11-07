package com.example.bookapi.domain.repository

import com.example.bookapi.data.model.ResultData
import com.example.bookapi.data.remote.dto.auth.request.SignInRequest
import com.example.bookapi.data.remote.dto.auth.request.SignInVerifyRequest
import com.example.bookapi.data.remote.dto.auth.request.SignUpRequest
import com.example.bookapi.data.remote.dto.auth.request.SignUpVerifyRequest
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun signUp(signUpRequest: SignUpRequest): Flow<ResultData<Unit>>

    suspend fun signUpVerify(signUpVerifyRequest: SignUpVerifyRequest): Flow<ResultData<Unit>>

    suspend fun signIn(signInRequest: SignInRequest): Flow<ResultData<Unit>>

    suspend fun signInVerify(signInVerifyRequest: SignInVerifyRequest): Flow<ResultData<Unit>>

    suspend fun isFirst():Boolean

}