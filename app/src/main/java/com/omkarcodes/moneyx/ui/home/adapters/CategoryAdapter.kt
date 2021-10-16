package com.omkarcodes.moneyx.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.omkarcodes.moneyx.R
import com.omkarcodes.moneyx.ui.home.Category

class CategoryAdapter(
    private val list: List<Category>,
    private val mContext: Context
) : ArrayAdapter<Category>(mContext,0,list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView
            ?: LayoutInflater.from(mContext).inflate(R.layout.item_category,parent,false)
        getItem(position)?.let { item ->

        }
        return view
    }

}