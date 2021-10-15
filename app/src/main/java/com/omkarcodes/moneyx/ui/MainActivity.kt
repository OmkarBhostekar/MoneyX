package com.omkarcodes.moneyx.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.omkarcodes.moneyx.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MoneyX)
        setContentView(R.layout.activity_main)
    }
}