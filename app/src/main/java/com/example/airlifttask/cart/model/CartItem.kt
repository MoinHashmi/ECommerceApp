package com.example.airlifttask.cart.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartItem(
    @PrimaryKey var id:Int,
    @ColumnInfo(name = "title")var title:String,
    @ColumnInfo(name = "price")var price:Double,
    @ColumnInfo(name = "image")var image:String,
    @ColumnInfo(name = "quantity")var quantity:Int):
    BaseCartItem {
    override fun getProductPriceByQty(): Double {
        return price*quantity
    }

    override fun increaseQty() {
        quantity.plus(1)
    }

    override fun decreaseQty() {
        if(quantity>1){
            quantity.minus(1)
        }
    }


}