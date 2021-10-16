package com.omkarcodes.moneyx.comman

import java.text.SimpleDateFormat
import java.util.*

fun String.toDateInMillis() : Long {
    val sdf = SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault())
    val date = sdf.parse(this)
    return date?.time ?: System.currentTimeMillis()
}

fun String.dateToMillis() : Long {
    val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val date = sdf.parse(this)
    return date?.time ?: System.currentTimeMillis()
}

fun String.monthToMillis(): Long {
    val sdf = SimpleDateFormat("MM-yyyy", Locale.getDefault())
    val date = sdf.parse(this)
    return date?.time ?: System.currentTimeMillis()
}