package com.omkarcodes.moneyx.ui.auth.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.omkarcodes.moneyx.R
import com.omkarcodes.moneyx.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment(R.layout.fragment_sign_up){

    private var _binding: FragmentSignUpBinding? = null
    private val binding: FragmentSignUpBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignUpBinding.bind(view)

        binding.apply {

            toolbar.btnBack.setOnClickListener { findNavController().popBackStack() }
            toolbar.tvTitle.text = "Sign Up"

            btnSignUp.setOnClickListener {
                findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToPasswordFragment())
            }

            tvLogin.setOnClickListener {
                findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment())
            }
        }
    }

     override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}