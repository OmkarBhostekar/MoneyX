package com.omkarcodes.moneyx.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.omkarcodes.moneyx.comman.Resource
import com.omkarcodes.moneyx.comman.monthToMillis
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _transactions = MutableLiveData<Resource<List<Transaction>>>()
    val transactions: LiveData<Resource<List<Transaction>>> = _transactions
    private val _monthList = MutableLiveData<List<String>>()
    val monthList: LiveData<List<String>> = _monthList
    private val _newTransaction = MutableLiveData<Resource<String>>()
    val newTransaction: LiveData<Resource<String>> = _newTransaction
    private lateinit var monthWiseMap: Map<String,List<Transaction>>

    fun getTransactions() = viewModelScope.launch(Dispatchers.IO) {
        _transactions.postValue(Resource.Loading())
        fireStore.collection("users")
            .document(firebaseAuth.currentUser!!.uid)
            .collection("transactions")
            .get()
            .addOnSuccessListener {
                val list = it.toObjects(Transaction::class.java)
                monthWiseMap = list.groupBy { t -> t.date.substring(t.date.indexOf("-")+1) }
                val orderedMonthList = monthWiseMap.keys.toMutableList()
                orderedMonthList.sortByDescending { m -> m.monthToMillis() }
                _monthList.postValue(orderedMonthList)
                _transactions.postValue(Resource.Success(monthWiseMap[orderedMonthList.first()]!!))
            }
            .addOnFailureListener {
                _transactions.postValue(Resource.Error("Unable to load transactions"))
            }
    }

    fun filterByMonth(key: String){
        _transactions.postValue(Resource.Success(monthWiseMap[key]!!))
    }

    fun getRecent() = viewModelScope.launch(Dispatchers.IO) {
        _transactions.postValue(Resource.Loading())
        fireStore.collection("users")
            .document(firebaseAuth.currentUser!!.uid)
            .collection("transactions")
            .get()
            .addOnSuccessListener {
                val list = it.toObjects(Transaction::class.java)
                _transactions.postValue(Resource.Success(list))
            }
            .addOnFailureListener {
                _transactions.postValue(Resource.Error("Unable to load transactions"))
            }
    }

    fun addTransaction(transaction: Transaction) = viewModelScope.launch(Dispatchers.IO) {
        fireStore.collection("users")
            .document(firebaseAuth.currentUser!!.uid)
            .collection("transactions")
            .add(transaction)
            .addOnSuccessListener {
                _newTransaction.postValue(Resource.Success("Transaction added"))
            }
            .addOnFailureListener {
                _newTransaction.postValue(Resource.Error("Unable to create new transactions"))
            }
    }

    fun clearNewTransaction() {
        _newTransaction.postValue(Resource.Empty())
    }

}