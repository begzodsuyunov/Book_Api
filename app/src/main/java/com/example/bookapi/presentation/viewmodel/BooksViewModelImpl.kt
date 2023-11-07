package com.example.bookapi.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapi.data.model.ResultData
import com.example.bookapi.data.remote.dto.book.request.ChangeFavRequest
import com.example.bookapi.data.remote.dto.book.request.DeleteBookRequest
import com.example.bookapi.data.remote.dto.book.request.PostBookRequest
import com.example.bookapi.data.remote.dto.book.request.PutBookRequest
import com.example.bookapi.data.remote.dto.book.response.AllBooks
import com.example.bookapi.data.remote.dto.book.response.PostBookResponse
import com.example.bookapi.domain.navigation.Navigator
import com.example.bookapi.domain.repository.BookRepository
import com.example.bookapi.presentation.book.vm.BooksViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModelImpl @Inject constructor(
    private val navigator: Navigator,
    private val repository: BookRepository
) : BooksViewModel, ViewModel() {

    override val isLoading =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val isConnecting =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val errorMsg =
        MutableSharedFlow<String>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)
    override val message =
        MutableSharedFlow<Boolean>(onBufferOverflow = BufferOverflow.DROP_OLDEST, replay = 1)

    override val getAllBooks = MutableSharedFlow<AllBooks>(
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
        replay = 1
    )
    override val postBookFlow = MutableSharedFlow<PostBookResponse>(
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
        replay = 1
    )

    override fun postBook(postBookRequest: PostBookRequest) {
        viewModelScope.launch {
            repository.postBook(postBookRequest).collectLatest {
                when (it) {
                    is ResultData.Fail -> errorMsg.emit(it.message)
                    is ResultData.HasConnection -> isConnecting.emit(it.hasConnection)
                    is ResultData.Loading -> isLoading.emit(it.isLoading)
                    is ResultData.Success -> {
                        it.data?.let { it1 -> postBookFlow.emit(it1) }
                        getBooks()
                    }
                }
            }
        }
    }

    override fun getBooks() {
        viewModelScope.launch {
            repository.getBooks().collectLatest {
                when (it) {
                    is ResultData.Fail -> errorMsg.emit(it.message)
                    is ResultData.HasConnection -> isConnecting.emit(it.hasConnection)
                    is ResultData.Loading -> isLoading.emit(it.isLoading)
                    is ResultData.Success -> it.data?.let { it1 -> getAllBooks.emit(it1) }
                }
            }
        }
    }

    override fun deleteBook(deleteBookRequest: DeleteBookRequest) {
        viewModelScope.launch {
            repository.deleteBook(deleteBookRequest).collectLatest {
                when (it) {
                    is ResultData.Fail -> errorMsg.emit(it.message)
                    is ResultData.HasConnection -> isConnecting.emit(it.hasConnection)
                    is ResultData.Loading -> isLoading.emit(it.isLoading)
                    is ResultData.Success -> getBooks()
                }
            }
        }
    }

    override fun putBook(putBookRequest: PutBookRequest) {
        viewModelScope.launch {
            repository.putBook(putBookRequest).collectLatest {
                when (it) {
                    is ResultData.Fail -> errorMsg.emit(it.message)
                    is ResultData.HasConnection -> isConnecting.emit(it.hasConnection)
                    is ResultData.Loading -> isLoading.emit(it.isLoading)
                    is ResultData.Success -> getBooks()
                }
            }
        }
    }

    override fun changeFavBook(changeFavRequest: ChangeFavRequest) {
        viewModelScope.launch {
            repository.changeFavBook(changeFavRequest).collectLatest {
                when (it) {
                    is ResultData.Fail -> errorMsg.emit(it.message)
                    is ResultData.HasConnection -> isConnecting.emit(it.hasConnection)
                    is ResultData.Loading -> isLoading.emit(it.isLoading)
                    is ResultData.Success -> {
                        Log.d("CHANGEF", "viewModel success-> ${changeFavRequest.bookId}")
                        getBooks()
                        Log.d("CHANGEF", "viewModel success2-> ${changeFavRequest.bookId}")

                    }
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            repository.getBooks().collectLatest {
                when (it) {
                    is ResultData.Fail -> errorMsg.emit(it.message)
                    is ResultData.HasConnection -> isConnecting.emit(it.hasConnection)
                    is ResultData.Loading -> isLoading.emit(it.isLoading)
                    is ResultData.Success -> it.data?.let { it1 -> getAllBooks.emit(it1) }
                }
            }
        }
    }
}