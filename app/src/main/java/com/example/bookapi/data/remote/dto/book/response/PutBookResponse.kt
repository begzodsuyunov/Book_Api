package com.example.bookapi.data.remote.dto.book.response

data class PutBookResponse(
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val pageCount: Int,
    val fav: Boolean = false
)
