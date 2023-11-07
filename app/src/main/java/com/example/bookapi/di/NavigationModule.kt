package com.example.bookapi.di

import com.example.bookapi.domain.navigation.NavigationDispatcher
import com.example.bookapi.domain.navigation.NavigationHandler
import com.example.bookapi.domain.navigation.Navigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun navigation(navigator: NavigationDispatcher): Navigator

    @Binds
    fun navigatorHandler(navigatorHandler: NavigationDispatcher): NavigationHandler
}