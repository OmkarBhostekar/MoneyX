package com.omkarcodes.moneyx.ui.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.omkarcodes.moneyx.R
import com.omkarcodes.moneyx.databinding.FragmentHomeBinding
import com.omkarcodes.moneyx.databinding.FragmentTransactionsBinding

class TransactionsFragment : Fragment(R.layout.fragment_transactions){

    private var _binding: FragmentTransactionsBinding? = null
    private val binding: FragmentTransactionsBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTransactionsBinding.bind(view)
    }

     override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}