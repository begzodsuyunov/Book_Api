package com.example.bookapi.data.remote.dto.book.request

data class PutBookRequest(
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val pageCount: Int
)
