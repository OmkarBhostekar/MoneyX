package com.omkarcodes.moneyx.ui.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.omkarcodes.moneyx.R
import com.omkarcodes.moneyx.databinding.FragmentNewTransactionBinding

class NewTransactionFragment : Fragment(R.layout.fragment_new_transaction){

    private var _binding: FragmentNewTransactionBinding? = null
    private val binding: FragmentNewTransactionBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewTransactionBinding.bind(view)

        binding.apply {

            editText.requestFocus()

        }
    }

     override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}