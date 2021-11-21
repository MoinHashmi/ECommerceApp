package com.example.airlifttask.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.airlifttask.R
import com.example.airlifttask.cart.handler.CartHandler
import com.example.airlifttask.cart.model.CartItem
import com.example.airlifttask.cart.viewModel.MainActivityViewModel
import com.example.airlifttask.data.AppDatabase
import com.example.airlifttask.data.CartItemDao
import com.example.airlifttask.home.model.Product
import com.example.airlifttask.utils.Constants.CURRENCY

class ProductAdapter(val mainViewModel: MainActivityViewModel) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var productList=ArrayList<Product>()
    private var cartHandler=CartHandler.INSTANCE

    class ProductViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView:ImageView=view.findViewById(R.id.imageView)
        val textViewCurrency:TextView=view.findViewById(R.id.textViewCurrency)
        val textViewPrice:TextView=view.findViewById(R.id.textViewPrice)
        val textViewTitle:TextView=view.findViewById(R.id.textViewTitle)
        val buttonAddToCart:Button=view.findViewById(R.id.buttonAddToCart)
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
            holder.buttonAddToCart.setOnClickListener {
                val item=CartItem(product.id,product.title,product.price,product.image,1)
                if(!cartHandler.hasItem(item)) {
                    cartHandler.addToCart(item)
                    mainViewModel.persistCartData(it.context, item)
                    mainViewModel.cartItemCount.postValue(cartHandler.getItemCount())
                    mainViewModel.cartItemList.postValue(cartHandler.cartItemList)
                }

            }
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