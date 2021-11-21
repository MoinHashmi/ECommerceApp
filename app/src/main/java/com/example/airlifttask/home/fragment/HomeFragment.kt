package com.example.airlifttask.home.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.airlifttask.R
import com.example.airlifttask.cart.handler.CartHandler
import com.example.airlifttask.home.adapter.TabLayoutAdapter
import com.example.airlifttask.home.viewModel.HomeViewModel
import com.example.airlifttask.network.ApiProvider
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var tabLayout:TabLayout
    private lateinit var viewPager:ViewPager2
    private lateinit var shimmerViewContainer: ShimmerFrameLayout

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

    private fun initUI(view: View) {

        tabLayout=view.findViewById(R.id.tabLayout)
        viewPager=view.findViewById(R.id.viewPager)
        shimmerViewContainer=view.findViewById(R.id.shimmerViewContainer)

    }

    private fun setupData(view: View) {

        viewModel.isLoading.observe(viewLifecycleOwner,{
            shimmerViewContainer.visibility=if(it) View.VISIBLE else View.GONE
        })

        viewModel.tabList.observe(viewLifecycleOwner,{
            val tabAdapter=TabLayoutAdapter(this)
            tabAdapter.setTabList(it)
            viewPager.apply {
                adapter=tabAdapter
            }
            TabLayoutMediator(tabLayout,viewPager){tab,position->
                tab.text=tabAdapter.getTitle(position)
            }.attach()
        })

        viewModel.errorMessage.observe(viewLifecycleOwner,{
            Toast.makeText(view.context,it,Toast.LENGTH_LONG).show()
        })


        if(ApiProvider.isNetworkConnected(view.context)){
            viewModel.getProducts()
        }
    }


}