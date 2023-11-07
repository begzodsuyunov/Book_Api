package com.example.bookapi.di

import com.example.bookapi.domain.usecase.BaseSignUseCase
import com.example.bookapi.domain.usecase.CheckPasswordUseCase
import com.example.bookapi.domain.usecase.SignUpUseCase
import com.example.bookapi.domain.usecase.impl.BaseSignUseCaseImpl
import com.example.bookapi.domain.usecase.impl.CheckPasswordUseCaseImpl
import com.example.bookapi.domain.usecase.impl.SignUpUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {
    @Binds
    @Singleton
    fun bindBaseUseCase(baseSignUseCaseImpl: BaseSignUseCaseImpl): BaseSignUseCase

    @Binds
    @Singleton
    fun bindCheckPasswordUseCase(checkPasswordUseCaseImpl: CheckPasswordUseCaseImpl): CheckPasswordUseCase

    @Binds
    @Singleton
    fun bindSignUpUseCase(signUpUseCaseImpl: SignUpUseCaseImpl): SignUpUseCase
}