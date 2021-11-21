package com.example.airlifttask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.airlifttask.cart.CartFragment
import com.example.airlifttask.cart.handler.CartHandler
import com.example.airlifttask.cart.model.CartItem
import com.example.airlifttask.cart.viewModel.MainActivityViewModel
import com.example.airlifttask.data.AppDatabase
import com.example.airlifttask.data.CartItemDao
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private var cartHandler= CartHandler.INSTANCE
    val model:MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model.getDataPersist(this)


        setUpNavigation()
    }

    fun setUpNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_nav)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        NavigationUI.setupWithNavController(
            bottomNavigationView,
            navHostFragment!!.navController
        )

        val badge = bottomNavigationView.getOrCreateBadge(R.id.cartFragment)

       model.cartItemCount.observe(this,{
            if (it>0){
                badge.isVisible = true
                badge.number = if (it>=99)99 else it
            }else{
                badge.isVisible = false
                badge.number = 0
            }
        })

        model.cartItemList.observe(this,{
            if(CartFragment.newInstance().isAdded){
                CartFragment.newInstance().setupData()
            }
        })

    }
}