package com.example.airlifttask.cart.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airlifttask.cart.handler.CartHandler
import com.example.airlifttask.cart.model.CartItem
import com.example.airlifttask.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel(){

    val cartHandler=CartHandler.INSTANCE

    val cartItemCount:MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val cartItemList:MutableLiveData<ArrayList<CartItem>> by lazy {
        MutableLiveData<ArrayList<CartItem>>()
    }

    fun getDataPersist(context: Context){
        viewModelScope.launch(Dispatchers.IO){
            val cartItemDao= AppDatabase.getInstance(context).cartItemDao()
            val lst=cartItemDao.getAll() as ArrayList<CartItem>

            if(lst.isNotEmpty()){
                viewModelScope.launch (Dispatchers.Main){
                    cartHandler.cartItemList= lst
                    cartItemCount.postValue(cartHandler.getItemCount())
                    cartItemList.postValue(lst)
                }
            }
        }
    }

    fun persistCartData(context: Context,item: CartItem){
        viewModelScope.launch (Dispatchers.IO){
            val cartItemDao= AppDatabase.getInstance(context).cartItemDao()
            cartItemDao.insert(item)
        }
    }

    fun updateItem(context: Context,item: CartItem){
        viewModelScope.launch (Dispatchers.IO){
            val cartItemDao= AppDatabase.getInstance(context).cartItemDao()
            cartItemDao.updateItem(item.id,item.title,item.price,item.image,item.quantity)
        }
    }

    fun removeItem(context: Context,item: CartItem){
        viewModelScope.launch (Dispatchers.IO){
            val cartItemDao= AppDatabase.getInstance(context).cartItemDao()
            cartItemDao.delete(item)
        }
    }
}