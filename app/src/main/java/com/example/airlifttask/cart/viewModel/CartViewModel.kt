package com.example.airlifttask.cart.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.airlifttask.cart.handler.CartHandler
import com.example.airlifttask.cart.model.CartItem

class CartViewModel : ViewModel() {

    val cartItemList= MutableLiveData<ArrayList<CartItem>>()
    val isLoading = MutableLiveData<Boolean>()
    val cartHandler=CartHandler.INSTANCE

    fun getCartItem(){
        cartItemList.postValue(cartHandler.cartItemList)
    }

}