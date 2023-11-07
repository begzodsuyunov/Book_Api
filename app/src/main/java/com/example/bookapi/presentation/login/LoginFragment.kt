package com.example.bookapi.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapi.R
import com.example.bookapi.data.remote.dto.auth.request.SignInRequest
import com.example.bookapi.databinding.FragmentLoginBinding
import com.example.bookapi.presentation.login.vm.LoginViewModel
import com.example.bookapi.presentation.viewmodel.LoginViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewBinding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)
    private val viewModel: LoginViewModel by viewModels<LoginViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.tvNew.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.openSignUpScreen()
            }
        }


        viewBinding.btnConfirm.setOnClickListener {
            val number = viewBinding.enterPhone.text.toString()
            val password = viewBinding.enterPassword.text.toString()

            viewModel.openMainScreen(SignInRequest(number, password))
        }


        viewModel.message.onEach {
            if (it) {
                Toast.makeText(requireContext(), "true", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Wrong information", Toast.LENGTH_SHORT).show()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

}