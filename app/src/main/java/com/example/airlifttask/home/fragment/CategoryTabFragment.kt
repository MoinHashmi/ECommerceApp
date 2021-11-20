package com.example.airlifttask.home.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.airlifttask.R
import com.example.airlifttask.home.viewModel.CategoryTabViewModel

class CategoryTabFragment : Fragment() {

    companion object {
        fun newInstance() = CategoryTabFragment()
    }

    private lateinit var viewModel: CategoryTabViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_tab_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoryTabViewModel::class.java)
    }

}