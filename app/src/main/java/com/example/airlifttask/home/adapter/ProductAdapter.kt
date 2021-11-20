package com.example.airlifttask.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.airlifttask.R
import com.example.airlifttask.home.model.Product
import com.example.airlifttask.utils.Constants.CURRENCY

class ProductAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var productList=ArrayList<Product>()

    class ProductViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView:ImageView=view.findViewById(R.id.imageView)
        val textViewCurrency:TextView=view.findViewById(R.id.textViewCurrency)
        val textViewPrice:TextView=view.findViewById(R.id.textViewPrice)
        val textViewTitle:TextView=view.findViewById(R.id.textViewTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.product_layout,parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product=productList[position]
        if(holder is ProductViewHolder){
            Glide
                .with(holder.imageView)
                .load(product.image)
                .into(holder.imageView)

            holder.textViewCurrency.text = CURRENCY
            holder.textViewPrice.text=product.price.toString()
            holder.textViewTitle.text=product.title
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setProductList(list:ArrayList<Product>){
        this.productList=list
        notifyDataSetChanged()
    }


}