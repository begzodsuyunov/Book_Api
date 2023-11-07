package com.example.bookapi.data.remote.dto.auth.request


data class SignUpRequest(
    val phone: String,
    val password: String,
    val lastName: String,
    val firstName: String
)
