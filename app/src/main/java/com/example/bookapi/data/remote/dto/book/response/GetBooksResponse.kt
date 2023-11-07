package com.example.bookapi.data.remote.dto.book.response

import com.google.gson.annotations.SerializedName

data class GetBooksResponse(
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val pageCount: Int,
    @SerializedName("fav")
    var isFav: Boolean
)
