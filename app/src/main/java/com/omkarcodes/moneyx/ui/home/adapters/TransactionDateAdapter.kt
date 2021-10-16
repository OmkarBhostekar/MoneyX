package com.omkarcodes.moneyx.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omkarcodes.moneyx.databinding.ItemTransactionGroupBinding

class TransactionDateAdapter : RecyclerView.Adapter<TransactionDateAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTransactionGroupBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return 10
    }

    inner class ViewHolder(private val binding: ItemTransactionGroupBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int){

            binding.apply {

                rvTransactions.layoutManager = LinearLayoutManager(root.context)
                rvTransactions.adapter = TransactionAdapter()
            }

        }
    }
}