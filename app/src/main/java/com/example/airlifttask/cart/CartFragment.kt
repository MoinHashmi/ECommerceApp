package com.example.airlifttask.cart

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import com.example.airlifttask.R
import com.example.airlifttask.cart.adapter.CartAdapter
import com.example.airlifttask.cart.handler.CartHandler
import com.example.airlifttask.cart.viewModel.CartViewModel
import com.example.airlifttask.cart.viewModel.MainActivityViewModel
import com.example.airlifttask.utils.Constants.CURRENCY

class CartFragment : Fragment() {

    companion object {
        fun newInstance() = CartFragment()
    }

    private lateinit var viewModel: CartViewModel
    private lateinit var recyclerView: ShimmerRecyclerView
    private lateinit var textViewTotal: TextView
    private lateinit var textViewTotalItem: TextView
    private lateinit var buttonCheckout: Button
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
        textViewTotal=view.findViewById(R.id.textViewTotal)
        textViewTotalItem=view.findViewById(R.id.textViewTotalItem)
        buttonCheckout=view.findViewById(R.id.buttonCheckout)
        cartAdapter= CartAdapter(mainViewModel,this)

        recyclerView.apply {
            layoutManager=LinearLayoutManager(this.context)
            adapter=cartAdapter
            isNestedScrollingEnabled=false
        }

        buttonCheckout.setOnClickListener {

        }
    }

    fun setupData() {
        cartAdapter.setCartItemList(cartHandler.cartItemList)
        textViewTotal.text="$CURRENCY ${cartHandler.getTotalAmount()}"
        textViewTotalItem.text=" (${cartHandler.getItemCount()} items)"

    }

}