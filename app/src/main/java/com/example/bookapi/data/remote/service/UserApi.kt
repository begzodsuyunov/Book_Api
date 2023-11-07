package com.example.bookapi.data.remote.service

import com.example.bookapi.data.remote.dto.user.request.PostRateRequest
import com.example.bookapi.data.remote.dto.user.request.PostUserBooksRequest
import com.example.bookapi.data.remote.dto.user.response.AllUsers
import com.example.bookapi.data.remote.dto.user.response.PostRateResponse
import com.example.bookapi.data.remote.dto.user.response.PostUserBooksResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @GET("books/users")
    suspend fun getUsers(): Response<AllUsers>

    @POST("books/user")
    suspend fun postUser(
        @Body postUserBooksRequest: PostUserBooksRequest
    ): Response<PostUserBooksResponse>

    @POST("book/rate")
    suspend fun rateBook(
        @Body postRateRequest: PostRateRequest
    ): Response<PostRateResponse>
}