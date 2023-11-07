package com.example.bookapi.domain.usecase

interface CheckPasswordUseCase {
    fun checkPassword(password: String): Boolean

}