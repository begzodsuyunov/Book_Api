package com.example.bookapi.domain.repository

import com.example.bookapi.data.model.ResultData
import com.example.bookapi.data.remote.dto.book.request.ChangeFavRequest
import com.example.bookapi.data.remote.dto.book.request.DeleteBookRequest
import com.example.bookapi.data.remote.dto.book.request.PostBookRequest
import com.example.bookapi.data.remote.dto.book.request.PutBookRequest
import com.example.bookapi.data.remote.dto.book.response.AllBooks
import com.example.bookapi.data.remote.dto.book.response.PostBookResponse
import kotlinx.coroutines.flow.Flow

interface BookRepository {

    suspend fun postBook(postBookRequest: PostBookRequest): Flow<ResultData<PostBookResponse>>

    suspend fun getBooks(): Flow<ResultData<AllBooks>>

//    suspend fun getBooks(): Flow<List<GetBooksResponse>>

    suspend fun deleteBook(deleteBookRequest: DeleteBookRequest): Flow<ResultData<Unit>>

    suspend fun putBook(putBookRequest: PutBookRequest): Flow<ResultData<Unit>>

    suspend fun changeFavBook(changeFavRequest: ChangeFavRequest): Flow<ResultData<Unit>>

}