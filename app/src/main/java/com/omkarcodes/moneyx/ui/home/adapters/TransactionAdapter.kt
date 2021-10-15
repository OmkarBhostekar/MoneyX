package com.omkarcodes.moneyx.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omkarcodes.moneyx.databinding.ItemTransactionBinding

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTransactionBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return 20
    }

    inner class ViewHolder(private val binding: ItemTransactionBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int){

        }
    }
}