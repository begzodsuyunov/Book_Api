package com.example.bookapi.data.remote.dto.auth.response

data class SignUpVerificationResponse(
    val accessToken: String,
    val refreshToken: String
)
