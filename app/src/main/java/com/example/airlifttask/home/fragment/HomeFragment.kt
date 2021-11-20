package com.example.airlifttask.home.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.airlifttask.R
import com.example.airlifttask.home.viewModel.HomeViewModel
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var tabLayout:TabLayout
    private lateinit var viewPager:ViewPager2

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

    }

    private fun initUI(view: View) {
        tabLayout=view.findViewById(R.id.tabLayout)
        viewPager=view.findViewById(R.id.viewPager)


    }


}