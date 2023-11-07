package com.example.bookapi.di

import com.example.bookapi.data.repositoryImpl.AuthRepositoryImpl
import com.example.bookapi.data.repositoryImpl.BookRepositoryImpl
import com.example.bookapi.data.repositoryImpl.UserRepositoryImpl
import com.example.bookapi.domain.repository.AuthRepository
import com.example.bookapi.domain.repository.BookRepository
import com.example.bookapi.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    fun provideBookRepository(
        bookRepositoryImpl: BookRepositoryImpl
    ): BookRepository

    @Binds
    @Singleton
    fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}