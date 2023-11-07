package com.example.bookapi.presentation.users.vm

import com.example.bookapi.data.remote.dto.user.response.AllUsers
import kotlinx.coroutines.flow.Flow

interface UsersViewModel {
    val isLoading: Flow<Boolean>
    val isConnecting: Flow<Boolean>
    val errorMsg: Flow<String>
    val message: Flow<Boolean>

    val getAllUsers: Flow<AllUsers>
}