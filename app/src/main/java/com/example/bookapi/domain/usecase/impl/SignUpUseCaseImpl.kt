package com.example.bookapi.domain.usecase.impl

import com.example.bookapi.domain.usecase.SignUpUseCase
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor() : SignUpUseCase {

    override fun checkName(name: String): Boolean = name.length >= 3


    override fun checkSamePassword(password1: String, password2: String): Boolean =
        password1.length >= 6 && password1 == password2

}