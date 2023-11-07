package com.example.bookapi.data.remote.dto.user.response

data class PostUserBooksResponse(
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val pageCount: Int,
    val isFav: Boolean,
    val isLike: Boolean,
    val likeCount: Int,
    val dislikeCount: Int
)
