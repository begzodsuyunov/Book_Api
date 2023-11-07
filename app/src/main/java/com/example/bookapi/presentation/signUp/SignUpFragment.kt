package com.example.bookapi.presentation.signUp

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
import com.example.bookapi.databinding.FragmentSignUpBinding
import com.example.bookapi.presentation.signUp.vm.SignUpViewModel
import com.example.bookapi.presentation.viewmodel.SignUpViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val viewBinding: FragmentSignUpBinding by viewBinding(FragmentSignUpBinding::bind)
    private val viewModel: SignUpViewModel by viewModels<SignUpViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.btnRegister.setOnClickListener {
            val firstName = viewBinding.enterFirstName.text.toString()
            val lastName = viewBinding.enterLastName.text.toString()
            val phoneNumber = viewBinding.enterPhone.text.toString()
            val password1 = viewBinding.enterPassword.text.toString()
            val password2 = viewBinding.reEnterPassword.text.toString()

            viewModel.openVerifyScreen(firstName, lastName, phoneNumber, password1, password2)
        }
        viewBinding.imageView2.setOnClickListener {
            viewModel.back()
        }

        viewModel.message.onEach {
            if (it) {
                Toast.makeText(requireContext(), "true", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "wrong information", Toast.LENGTH_SHORT).show()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }



}