package com.example.bookapi.presentation.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bookapi.presentation.book.BooksFragment
import com.example.bookapi.presentation.users.UsersFragment

class PagerAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BooksFragment()
            else -> UsersFragment()
        }
    }
}