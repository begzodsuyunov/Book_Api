package com.example.bookapi.di

import android.content.Context
import androidx.room.Room
import com.example.bookapi.data.local.room.BookDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDatabaseModule {

    @Provides
    @Singleton
    fun providesLocaleDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, BookDatabase::class.java, "AppDatabase").build()

    @Provides
    @Singleton
    fun providesDao(db: BookDatabase) = db.bookDao()
}