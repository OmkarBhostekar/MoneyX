package com.omkarcodes.moneyx.ui.home

data class Transaction(
    val categoryId: Int,
    val amount: Long,
    val time: String,
    val date: String,
    val description: String,
    val category: String,
)
