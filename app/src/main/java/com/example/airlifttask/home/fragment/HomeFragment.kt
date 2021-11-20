package com.example.airlifttask.home.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import com.example.airlifttask.R
import com.example.airlifttask.home.adapter.ProductAdapter
import com.example.airlifttask.home.viewModel.HomeViewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var recyclerView:RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var shimmerRecyclerView: ShimmerRecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        initUI(view)

        setupData(view)

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun initUI(view: View) {
        shimmerRecyclerView=view.findViewById(R.id.shimmerRecyclerView)
        recyclerView=view.findViewById(R.id.recyclerView)

        recyclerView.apply {
            layoutManager=GridLayoutManager(this.context,2)
            productAdapter= ProductAdapter()
            adapter=productAdapter
        }
    }

    private fun setupData(view: View) {

        viewModel.isLoading.observe(viewLifecycleOwner,{
            shimmerRecyclerView.visibility=if(it) View.VISIBLE else View.GONE
        })

        viewModel.productList.observe(viewLifecycleOwner,{
            productAdapter.setProductList(it)
        })


        viewModel.getProducts()
    }


}