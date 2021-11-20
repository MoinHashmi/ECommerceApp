package com.example.airlifttask.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import com.example.airlifttask.R
import com.example.airlifttask.cart.viewModel.MainActivityViewModel
import com.example.airlifttask.home.adapter.ProductAdapter
import com.example.airlifttask.home.model.Product

class CategoryFragment : Fragment() {

    companion object {
        fun newInstance() = CategoryFragment()
    }

    private lateinit var productAdapter: ProductAdapter
    private lateinit var shimmerRecyclerView: ShimmerRecyclerView
    private lateinit var productList:ArrayList<Product>
    private lateinit var mainViewModel:MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productList= arguments?.get("prodList") as ArrayList<Product>

        activity?.let {
            mainViewModel=ViewModelProviders.of(it)[MainActivityViewModel::class.java]
        }
        initUI(view)

        setupData(view)

    }

    private fun initUI(view: View) {
        shimmerRecyclerView=view.findViewById(R.id.shimmerRecyclerView)

        shimmerRecyclerView.apply {
            layoutManager= GridLayoutManager(this.context,2)
            productAdapter= ProductAdapter(mainViewModel)
            adapter=productAdapter
            isNestedScrollingEnabled=false
        }
    }

    private fun setupData(view: View) {
        productAdapter.setProductList(productList)
    }

}