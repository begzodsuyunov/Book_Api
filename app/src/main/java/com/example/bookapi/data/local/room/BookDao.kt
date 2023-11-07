package com.example.bookapi.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface BookDao {

    @Insert
    fun insert(bookEntity: BookEntity)

    @Insert
    fun insert(books: List<BookEntity>)

    @Update
    fun update(bookEntity: BookEntity)

    @Query("DELETE FROM BookEntity")
    fun delete()

    @Query("SELECT * FROM BookEntity WHERE state !=2")
    fun getBooks(): List<BookEntity>

}