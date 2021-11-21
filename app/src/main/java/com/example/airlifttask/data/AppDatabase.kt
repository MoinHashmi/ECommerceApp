package com.example.airlifttask.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.airlifttask.cart.handler.CartHandler
import com.example.airlifttask.cart.model.CartItem

@Database(entities = [CartItem::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    companion object{

        fun getInstance(context: Context):AppDatabase{
            val INSTANCE: AppDatabase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { Room.databaseBuilder(
                context,
                AppDatabase::class.java, "AT-db"
            ).build() }

            return INSTANCE
        }

    }

    abstract fun cartItemDao(): CartItemDao
}