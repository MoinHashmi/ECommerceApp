package com.example.airlifttask.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airlifttask.R
import com.example.airlifttask.home.model.Product

class ProductAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var productList:ArrayList<Product>

    class ProductViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.product_layout,parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ProductViewHolder){

        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setProductList(list:ArrayList<Product>){
        this.productList=list
    }


}