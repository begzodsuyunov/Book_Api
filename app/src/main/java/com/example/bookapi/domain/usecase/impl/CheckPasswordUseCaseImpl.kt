package com.example.bookapi.domain.usecase.impl

import com.example.bookapi.domain.usecase.CheckPasswordUseCase
import javax.inject.Inject

class CheckPasswordUseCaseImpl @Inject constructor() : CheckPasswordUseCase {
    override fun checkPassword(password: String): Boolean =
        password.length >= 6

}