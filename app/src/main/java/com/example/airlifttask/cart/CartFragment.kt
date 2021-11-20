package com.example.airlifttask.cart

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import com.example.airlifttask.R
import com.example.airlifttask.cart.handler.CartHandler
import com.example.airlifttask.cart.viewModel.CartViewModel
import com.example.airlifttask.cart.viewModel.MainActivityViewModel

class CartFragment : Fragment() {

    companion object {
        fun newInstance() = CartFragment()
    }

    private lateinit var viewModel: CartViewModel
    private lateinit var recyclerView: ShimmerRecyclerView
    private lateinit var mainViewModel: MainActivityViewModel
    private lateinit var cartAdapter: CartAdapter
    private val cartHandler=CartHandler.INSTANCE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cart_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        activity?.let {
            mainViewModel= ViewModelProviders.of(it)[MainActivityViewModel::class.java]
        }

        initUI(view)

        setupData()

    }

    override fun onResume() {
        super.onResume()
        cartAdapter.setCartItemList(cartHandler.cartItemList)
    }

    private fun initUI(view: View) {
        recyclerView=view.findViewById(R.id.recyclerView)

        recyclerView.apply {
            layoutManager=LinearLayoutManager(this.context)
            cartAdapter= CartAdapter(mainViewModel)
            adapter=cartAdapter
            isNestedScrollingEnabled=false
        }
    }

    fun setupData() {
        Log.i("CART_LIST"," cart list size is: ${cartHandler.cartItemList.size}")
        cartAdapter.setCartItemList(cartHandler.cartItemList)
    }

}