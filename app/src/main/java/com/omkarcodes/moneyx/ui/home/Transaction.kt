package com.omkarcodes.moneyx.ui.home

data class Transaction(
    val categoryId: Int = 0,
    val amount: String = "",
    val time: String = "",
    val date: String = "",
    val description: String = "",
    val type: String = "",
)
