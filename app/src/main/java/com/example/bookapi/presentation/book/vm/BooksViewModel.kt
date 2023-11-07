package com.example.bookapi.presentation.book.vm

import com.example.bookapi.data.remote.dto.book.request.ChangeFavRequest
import com.example.bookapi.data.remote.dto.book.request.DeleteBookRequest
import com.example.bookapi.data.remote.dto.book.request.PostBookRequest
import com.example.bookapi.data.remote.dto.book.request.PutBookRequest
import com.example.bookapi.data.remote.dto.book.response.AllBooks
import com.example.bookapi.data.remote.dto.book.response.PostBookResponse
import kotlinx.coroutines.flow.Flow

interface BooksViewModel {

    val isLoading: Flow<Boolean>
    val isConnecting: Flow<Boolean>
    val errorMsg: Flow<String>
    val message: Flow<Boolean>

    val getAllBooks: Flow<AllBooks>
    val postBookFlow: Flow<PostBookResponse>

    fun postBook(postBookRequest: PostBookRequest)

    fun getBooks()

    fun deleteBook(deleteBookRequest: DeleteBookRequest)

    fun putBook(putBookRequest: PutBookRequest)

    fun changeFavBook(changeFavRequest: ChangeFavRequest)
}