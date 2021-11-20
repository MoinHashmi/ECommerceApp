package com.example.airlifttask.cart.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.airlifttask.cart.model.CartItem

class MainActivityViewModel : ViewModel(){

    val cartItemCount:MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val cartItemList:MutableLiveData<ArrayList<CartItem>> by lazy {
        MutableLiveData<ArrayList<CartItem>>()
    }

}