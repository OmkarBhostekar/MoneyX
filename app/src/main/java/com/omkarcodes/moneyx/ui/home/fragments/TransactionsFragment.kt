package com.omkarcodes.moneyx.ui.home.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.omkarcodes.moneyx.R
import com.omkarcodes.moneyx.comman.Resource
import com.omkarcodes.moneyx.comman.dateToMillis
import com.omkarcodes.moneyx.comman.monthToMillis
import com.omkarcodes.moneyx.comman.toDateInMillis
import com.omkarcodes.moneyx.databinding.FragmentHomeBinding
import com.omkarcodes.moneyx.databinding.FragmentTransactionsBinding
import com.omkarcodes.moneyx.ui.home.HomeViewModel
import com.omkarcodes.moneyx.ui.home.Transaction
import com.omkarcodes.moneyx.ui.home.adapters.TransactionAdapter
import com.omkarcodes.moneyx.ui.home.adapters.TransactionDateAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class TransactionsFragment : Fragment(R.layout.fragment_transactions), TransactionAdapter.OnClickListener {

    private var _binding: FragmentTransactionsBinding? = null
    private val binding: FragmentTransactionsBinding
        get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTransactionsBinding.bind(view)

        viewModel.transactions.observe(viewLifecycleOwner,{
            when(it){
                is Resource.Success -> {
                    binding.apply {
                        val data = it.data!!.groupBy { t -> t.date }
                        val keys = data.keys.toMutableList()
                        keys.sortByDescending { d -> d.dateToMillis() }
                        rvTransactions.layoutManager = LinearLayoutManager(requireContext())
                        rvTransactions.adapter = TransactionDateAdapter(
                            data,
                            keys,
                            getDate(System.currentTimeMillis()),
                            getDate(System.currentTimeMillis()-86400000),
                        this@TransactionsFragment)
                    }
                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        })

        viewModel.monthList.observe(viewLifecycleOwner,{
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = it.first().monthToMillis()
            binding.btnMonth.text = calendar.getDisplayName(Calendar.MONTH,Calendar.LONG,Locale.getDefault())
        })

    }

    private fun getDate(timeInMillis: Long): String {
        return SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date(timeInMillis))
    }

    override fun onClick(transaction: Transaction) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(transaction))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}