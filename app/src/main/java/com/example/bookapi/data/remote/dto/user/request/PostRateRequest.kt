package com.example.bookapi.data.remote.dto.user.request

data class PostRateRequest(
    val bookId: Int,
    val isLike: Boolean
)
