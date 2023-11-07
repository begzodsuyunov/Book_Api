package com.example.bookapi.data.remote.service

import com.example.bookapi.data.remote.dto.book.request.ChangeFavRequest
import com.example.bookapi.data.remote.dto.book.request.DeleteBookRequest
import com.example.bookapi.data.remote.dto.book.request.PostBookRequest
import com.example.bookapi.data.remote.dto.book.request.PutBookRequest
import com.example.bookapi.data.remote.dto.book.response.AllBooks
import com.example.bookapi.data.remote.dto.book.response.ChangeFavResponse
import com.example.bookapi.data.remote.dto.book.response.DeleteBookResponse
import com.example.bookapi.data.remote.dto.book.response.PostBookResponse
import com.example.bookapi.data.remote.dto.book.response.PutBookResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.PUT

interface BookApi {

    @POST("book")
    suspend fun postBook(
        @Body postBookRequest: PostBookRequest
    ): Response<PostBookResponse>


    @GET("books")
    suspend fun getBooks(
//        @Header("Authorization") bearerToken: String,
    ): Response<AllBooks>

    @HTTP(method = "DELETE", path = "book", hasBody = true)
    suspend fun deleteBook(
        @Body deleteBookRequest: DeleteBookRequest
    ): Response<DeleteBookResponse>

    @PUT("book")
    suspend fun putBook(
        @Body putBookRequest: PutBookRequest
    ): Response<PutBookResponse>

    @POST("book/change-fav")
    suspend fun changeFavBook(
        @Body changeFavRequest: ChangeFavRequest
    ): Response<ChangeFavResponse>
}