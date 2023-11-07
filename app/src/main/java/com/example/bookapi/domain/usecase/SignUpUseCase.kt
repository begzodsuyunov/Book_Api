package com.example.bookapi.domain.usecase

interface SignUpUseCase {
    fun checkName(name: String): Boolean

    fun checkSamePassword(password1: String, password2: String): Boolean
}