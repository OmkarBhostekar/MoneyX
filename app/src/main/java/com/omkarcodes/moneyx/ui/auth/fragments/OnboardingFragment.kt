package com.omkarcodes.moneyx.ui.auth.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.omkarcodes.moneyx.R
import com.omkarcodes.moneyx.databinding.FragmentOnBoardingBinding
import com.omkarcodes.moneyx.ui.auth.adapters.OnboardingAdapter

class OnboardingFragment : Fragment(R.layout.fragment_on_boarding){

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding: FragmentOnBoardingBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOnBoardingBinding.bind(view)

        binding.apply {

            vpOnboarding.adapter = OnboardingAdapter()
            dotIndicator.setViewPager2(vpOnboarding)

            btnSignUp.setOnClickListener {
                findNavController().navigate(OnboardingFragmentDirections.actionOnboardingFragmentToSignUpFragment())
            }

            btnLogin.setOnClickListener {
                findNavController().navigate(OnboardingFragmentDirections.actionOnboardingFragmentToLoginFragment())
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser != null){
            findNavController().navigate(OnboardingFragmentDirections.actionOnboardingFragmentToPasswordFragment())
        }
    }

     override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}