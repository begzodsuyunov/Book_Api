package com.example.bookapi.domain.repository

import com.example.bookapi.data.model.ResultData
import com.example.bookapi.data.remote.dto.user.request.PostRateRequest
import com.example.bookapi.data.remote.dto.user.request.PostUserBooksRequest
import com.example.bookapi.data.remote.dto.user.response.AllUsers
import com.example.bookapi.data.remote.dto.user.response.PostRateResponse
import com.example.bookapi.data.remote.dto.user.response.PostUserBooksResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface UserRepository {

    suspend fun getUsers(): Flow<ResultData<AllUsers>>

    suspend fun postUser(postUserBooksRequest: PostUserBooksRequest): Response<PostUserBooksResponse>

    suspend fun rateBook(postRateRequest: PostRateRequest): Response<PostRateResponse>

}