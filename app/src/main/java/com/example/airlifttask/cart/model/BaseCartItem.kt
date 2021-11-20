package com.example.airlifttask.cart.model

interface BaseCartItem {
    fun getProductPriceByQty():Double
    fun increaseQty()
    fun decreaseQty()
}