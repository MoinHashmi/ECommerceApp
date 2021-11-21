package com.example.airlifttask.home.model

import java.io.Serializable

data class Product(
    val id:Int,
    val title:String,
    val price:Double,
    val category:String,
    val description:String,
    val image:String,
) : Serializable
