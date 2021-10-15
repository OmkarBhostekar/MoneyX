package com.omkarcodes.moneyx.ui.auth.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.omkarcodes.moneyx.R
import com.omkarcodes.moneyx.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login){

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)

        binding.apply {

            toolbar.btnBack.setOnClickListener { findNavController().popBackStack() }
            toolbar.tvTitle.text = "Login"

            btnLogin.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToPasswordFragment())
            }

            btnForgot.setOnClickListener {

            }

            tvSignUp.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
            }

        }
    }

     override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}