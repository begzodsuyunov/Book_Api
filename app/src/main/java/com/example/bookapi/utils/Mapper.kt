package com.example.bookapi.utils

import com.example.bookapi.data.local.room.BookEntity
import com.example.bookapi.data.local.room.State
import com.example.bookapi.data.remote.dto.book.response.GetBooksResponse


fun BookEntity.entityToResponse() = GetBooksResponse(id, title, author, description, pageCount, fav)

fun GetBooksResponse.responseToEntity() = BookEntity(
    id = id,
    title = title,
    author = author,
    description = description,
    pageCount = pageCount,
    fav = isFav,
    state = State.UpToDate
)