package com.example.bookapi.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val localId: Int = 0,
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val pageCount: Int,
    var fav: Boolean = false,
    val state: State,
)