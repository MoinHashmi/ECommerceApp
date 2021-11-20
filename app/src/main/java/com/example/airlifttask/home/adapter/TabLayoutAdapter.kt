package com.example.airlifttask.home.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.airlifttask.home.model.CategoryTab

class TabLayoutAdapter(fragment: Fragment):FragmentStateAdapter(fragment) {
    private var tabList=ArrayList<CategoryTab>()

    override fun getItemCount(): Int {
        return tabList.size
    }

    override fun createFragment(position: Int): Fragment {
        return tabList[position].fragment
    }

    fun setTabList(tabList:ArrayList<CategoryTab>){
        this.tabList=tabList
    }

    fun getTitle(position:Int):String{
        return tabList[position].title
    }
}