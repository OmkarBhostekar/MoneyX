package com.omkarcodes.moneyx.ui.home.fragments

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.omkarcodes.moneyx.R
import com.omkarcodes.moneyx.databinding.FragmentNewTransactionBinding

class NewTransactionFragment : Fragment(R.layout.fragment_new_transaction){

    private var _binding: FragmentNewTransactionBinding? = null
    private val binding: FragmentNewTransactionBinding
        get() = _binding!!
    private val args: NewTransactionFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewTransactionBinding.bind(view)

        binding.apply {

            editText.requestFocus()

            if (!args.isIncome){
                val red = ContextCompat.getColor(requireContext(),R.color.expenseRed)
                mainCl.setBackgroundColor(red)
                btnContinue.setBackgroundColor(red)
                tvTitle.text = "Expense"
            }

            btnBack.setOnClickListener { findNavController().popBackStack() }

        }
    }

     override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}