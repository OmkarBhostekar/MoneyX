package com.omkarcodes.moneyx.ui.home.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.omkarcodes.moneyx.R
import com.omkarcodes.moneyx.comman.Resource
import com.omkarcodes.moneyx.comman.monthToMillis
import com.omkarcodes.moneyx.comman.toDateInMillis
import com.omkarcodes.moneyx.databinding.FragmentHomeBinding
import com.omkarcodes.moneyx.ui.home.HomeViewModel
import com.omkarcodes.moneyx.ui.home.Transaction
import com.omkarcodes.moneyx.ui.home.adapters.TransactionAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home),TransactionAdapter.OnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        viewModel.getTransactions()

        binding.rvRecent.layoutManager = LinearLayoutManager(requireContext())

        val currentMonth = SimpleDateFormat("MM-yyyy", Locale.getDefault()).format(Date())

        viewModel.transactions.observe(viewLifecycleOwner,{
            when(it){
                is Resource.Success -> {
                    setupHomeScreen(it.data,currentMonth)
                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {

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

    @SuppressLint("SetTextI18n")
    private fun setupHomeScreen(data: List<Transaction>?, currentMonth: String) {
        data?.let { list ->
            var income = 0
            var expenses = 0
            list.forEach { t ->
                if (t.type == "income")
                    income += t.amount.toInt()
                else
                    expenses += t.amount.toInt()
            }
            val balance = income - expenses
            val filteredList = list.sortedByDescending { t -> (t.date+ " "+t.time).toDateInMillis() }

            binding.apply {
                tvBalance.text = "₹ $balance"
                tvIncome.text = "₹ $income"
                tvExpense.text = "₹ $expenses"
                rvRecent.adapter = TransactionAdapter(filteredList,this@HomeFragment)
                btnSeeAll.setOnClickListener {
                    viewModel.seeAllClicked.postValue(true)
                }
            }
        }
    }

    override fun onClick(transaction: Transaction) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(transaction))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}