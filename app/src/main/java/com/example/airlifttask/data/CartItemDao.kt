package com.example.airlifttask.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.airlifttask.cart.model.CartItem

@Dao
interface CartItemDao {
    @Query("SELECT * FROM CartItem")
    fun getAll(): List<CartItem>

    @Query("SELECT * FROM CartItem WHERE id IN (:itemIDs)")
    fun loadAllByIds(itemIDs: IntArray): List<CartItem>

    @Query("SELECT * FROM CartItem WHERE id=:id LIMIT 1")
    fun findByID(id: Int): CartItem

    @Query("UPDATE CartItem SET title = :title,price=:price,image=:image,quantity=:quantity WHERE id = :id")
    fun updateItem(id: Int,title:String,price:Double,image:String,quantity:Int)

    @Insert
    fun insert(cartItems: CartItem)

    @Insert
    fun insertAll(cartItems: ArrayList<CartItem>)

    @Delete
    fun delete(cartItem: CartItem)
}