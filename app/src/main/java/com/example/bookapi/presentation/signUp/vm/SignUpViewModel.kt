package com.example.bookapi.presentation.signUp.vm

import kotlinx.coroutines.flow.Flow

interface SignUpViewModel {


    val isLoading: Flow<Boolean>
    val isConnecting: Flow<Boolean>
    val errorMsg: Flow<String>
    val message: Flow<Boolean>

    fun back()
    fun openVerifyScreen(firstName:String, lastName:String, number:String, password1:String, password2: String)

}