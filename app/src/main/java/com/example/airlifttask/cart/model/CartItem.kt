package com.example.airlifttask.cart.model


data class CartItem(var id:Int,var title:String,var price:Double,var image:String,var quantity:Int):
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