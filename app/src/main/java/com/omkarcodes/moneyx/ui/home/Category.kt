package com.omkarcodes.moneyx.ui.home

import com.omkarcodes.moneyx.R

sealed class Category(val icon: Int,val color: Int,val bg: Int,val title: String,val id: Int){
    object Shopping : Category(R.drawable.ic_shopping,R.color.colorYellow,R.color.colorLightYellow,"Shopping",1)
    object Subscription : Category(R.drawable.ic_subscription,R.color.colorPrimary,R.color.colorLightPurple,"Subscription",2)
    object Food : Category(R.drawable.ic_food,R.color.colorRed,R.color.colorLightRed,"Food",3)
    object Transport : Category(R.drawable.ic_transport,R.color.colorBlue,R.color.colorLightBlue,"Transport",4)
    object Personal : Category(R.drawable.ic_personal,R.color.colorOrange,R.color.colorLightOrange,"Personal",5)
    object Salary : Category(R.drawable.ic_salary,R.color.colorGreen,R.color.colorLightGreen,"Salary",6)
    object Other : Category(R.drawable.ic_transaction,R.color.colorPink,R.color.colorLightPink,"Other",7)
}
