package com.example.airlifttask.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.airlifttask.home.model.CategoryTab

class TabLayoutAdapter(activity:FragmentActivity): FragmentStateAdapter(activity) {

    private val fragmentList=ArrayList<CategoryTab>()

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position].fragment
    }

    fun getTabTitle(position: Int):String{
        return fragmentList[position].title
    }

    fun addTab(tab:CategoryTab){
        fragmentList.add(tab)
    }

}