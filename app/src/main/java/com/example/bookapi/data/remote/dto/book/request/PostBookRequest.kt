package com.example.bookapi.data.remote.dto.book.request

import android.icu.text.CaseMap.Title

data class PostBookRequest(
    val title: String,
    val author: String,
    val description: String,
    val pageCount: Int
)