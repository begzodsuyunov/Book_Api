package com.example.bookapi.presentation.users

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapi.R
import com.example.bookapi.databinding.FragmentUsersBinding
import com.example.bookapi.presentation.users.vm.UsersViewModel
import com.example.bookapi.presentation.viewmodel.UsersViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class UsersFragment : Fragment(R.layout.fragment_users) {
    private val viewBinding: FragmentUsersBinding by viewBinding(FragmentUsersBinding::bind)

    private val viewModel: UsersViewModel by viewModels<UsersViewModelImpl>()

    private val adapter by lazy { UsersAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.recyclerView.adapter = adapter
        viewModel.getAllUsers.onEach {
            adapter.submitList(it)
            Log.d("BRRR", "submitted it -> $it")

        }.launchIn(viewLifecycleOwner.lifecycleScope)

        Log.d("BRRR", "neytral")
    }
}