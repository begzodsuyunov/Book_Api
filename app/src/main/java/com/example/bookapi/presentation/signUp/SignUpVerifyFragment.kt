package com.example.bookapi.presentation.signUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapi.R
import com.example.bookapi.data.remote.dto.auth.request.SignUpVerifyRequest
import com.example.bookapi.databinding.FragmentSignUpVerifyBinding
import com.example.bookapi.presentation.signUp.vm.VerifyViewModel
import com.example.bookapi.presentation.viewmodel.VerifyViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpVerifyFragment : Fragment(R.layout.fragment_sign_up_verify) {

    private val viewBinding: FragmentSignUpVerifyBinding by viewBinding(FragmentSignUpVerifyBinding::bind)
    private val viewModel: VerifyViewModel by viewModels<VerifyViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.btnCheck.setOnClickListener {
            val code = viewBinding.smsCodeView.enteredCode
            viewModel.checkCode(SignUpVerifyRequest(code))
        }
    }

}