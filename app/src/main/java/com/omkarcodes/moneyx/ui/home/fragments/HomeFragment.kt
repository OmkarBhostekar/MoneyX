package com.omkarcodes.moneyx.ui.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.omkarcodes.moneyx.R
import com.omkarcodes.moneyx.databinding.FragmentHomeBinding
import com.omkarcodes.moneyx.ui.home.adapters.TransactionAdapter

class HomeFragment : Fragment(R.layout.fragment_home){

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        binding.apply {

            rvRecent.layoutManager = LinearLayoutManager(requireContext())
            rvRecent.adapter = TransactionAdapter()

        }
    }

     override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}