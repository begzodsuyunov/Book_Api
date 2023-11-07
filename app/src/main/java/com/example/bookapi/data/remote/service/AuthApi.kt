package com.example.bookapi.data.remote.service

import com.example.bookapi.data.remote.dto.auth.request.SignInRequest
import com.example.bookapi.data.remote.dto.auth.request.SignInVerifyRequest
import com.example.bookapi.data.remote.dto.auth.request.SignUpRequest
import com.example.bookapi.data.remote.dto.auth.request.SignUpVerifyRequest
import com.example.bookapi.data.remote.dto.auth.response.SignInResponse
import com.example.bookapi.data.remote.dto.auth.response.SignInVerifyResponse
import com.example.bookapi.data.remote.dto.auth.response.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/sign-up")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ): Response<SignUpResponse>

    @POST("auth/sign-up/verify")
    suspend fun signUpVerify(
        @Header("Authorization") bearerToken: String,
        @Body signUpVerifyRequest: SignUpVerifyRequest
    ): Response<SignUpResponse>


    @POST("auth/sign-in")
    suspend fun signIn(
        @Body signInRequest: SignInRequest
    ): Response<SignInResponse>


    @POST("auth/sign-in/verify")
    suspend fun signInVerify(
        @Header("Authorization") bearerToken: String,
        @Body signInVerifyRequest: SignInVerifyRequest
    ): Response<SignInVerifyResponse>
}