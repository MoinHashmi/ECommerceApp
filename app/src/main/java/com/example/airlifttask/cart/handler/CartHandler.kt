package com.example.airlifttask.cart.handler

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.room.Room
import com.example.airlifttask.cart.model.CartItem
import com.example.airlifttask.cart.viewModel.MainActivityViewModel
import com.example.airlifttask.data.AppDatabase
import kotlin.math.roundToInt


class CartHandler private constructor(){
    companion object{
        val INSTANCE: CartHandler by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { CartHandler() }
    }

    private val TAG="CartHandler"

    var cartItemList=ArrayList<CartItem>()


    fun addToCart(item: CartItem){
        if(!hasItem(item)){
            cartItemList.add(item)
            Log.i(TAG, "added to cart")
        }else{
            val index=getItemIndex(item)
            cartItemList[index]=item
        }
    }

    fun removeFromCart(item: CartItem){
        removeItem(item)
    }

    fun getItemCount():Int{
        return cartItemList.size
    }

    fun getTotalAmount():Int{
        return cartItemList.sumBy {
            it.getProductPriceByQty().roundToInt()
        }
    }

    private fun removeItem(item: CartItem){
        for (i in 0 until cartItemList.size){
            if (cartItemList[i].id==item.id){
                cartItemList.removeAt(i)
                break
            }
        }
    }

    fun hasItem(item: CartItem):Boolean{
        cartItemList.forEach{
            if(it.id==item.id)return true
        }
        return false
    }

    private fun getItemIndex(item: CartItem):Int{
        for (i in 0 until cartItemList.size){
            if (cartItemList[i].id==item.id){
                return i
            }
        }
        return -1
    }


}