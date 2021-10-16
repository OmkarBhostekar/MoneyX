package com.omkarcodes.moneyx.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    fun getTransactions() = viewModelScope.launch(Dispatchers.IO) {
        fireStore.collection("users")
            .document(firebaseAuth.currentUser!!.uid)
            .collection("transactions")
            .get()
            .addOnSuccessListener {

            }
    }

    fun addTransaction(transaction: Transaction) = viewModelScope.launch(Dispatchers.IO) {
        fireStore.collection("users")
            .document(firebaseAuth.currentUser!!.uid)
            .collection("transactions")
            .add(transaction)
            .addOnSuccessListener {

            }
            .addOnFailureListener {

            }
    }

}