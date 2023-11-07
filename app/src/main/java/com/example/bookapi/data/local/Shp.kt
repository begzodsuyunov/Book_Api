package com.example.bookapi.data.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Shp @Inject constructor(@ApplicationContext context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences("Local_storage", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()


    var token: String
        get() = sharedPreferences.getString(
            "TOKEN",
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo2NCwiaXNzIjoiaHR0cDovLzEyNy4wLjAuMTo4MDgyLyIsImV4cCI6MTY5NjAwNDE5Mn0.t-4xv6o7LxfHGYuLsxOyfYzlvwLyCyGqPpaT6qf5LSU"
        )!!
        set(value) = editor.putString("TOKEN", value).apply()

    var isFirstOpen: Boolean
        get() = sharedPreferences.getBoolean("FIRST", true)
        set(value) = editor.putBoolean("FIRST", value).apply()

}