package com.example.airlifttask.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.airlifttask.R
import com.example.airlifttask.cart.handler.CartHandler
import com.example.airlifttask.cart.model.CartItem
import com.example.airlifttask.cart.viewModel.MainActivityViewModel
import com.example.airlifttask.utils.Constants.CURRENCY
import kotlin.math.roundToInt

class CartAdapter(val mainViewModel: MainActivityViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var cartItemList=ArrayList<CartItem>()
    private val cartHandler=CartHandler.INSTANCE

    class CartItemViewHolder(view: View):RecyclerView.ViewHolder(view){
        val imageView: ImageView =view.findViewById(R.id.imageView)
        val textViewTitle: TextView =view.findViewById(R.id.textViewTitle)
        val buttonDelete: LinearLayout =view.findViewById(R.id.buttonDelete)
        val textViewPriceIntoQty: TextView =view.findViewById(R.id.textViewPriceIntoQty)
        val textViewPrice: TextView =view.findViewById(R.id.textViewPrice)
        val textViewQty: TextView =view.findViewById(R.id.textViewQty)
        val buttonRemove: LinearLayout =view.findViewById(R.id.buttonRemove)
        val buttonAdd: LinearLayout =view.findViewById(R.id.buttonAdd)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.layout_cart_item,parent,false)
        return CartItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item=cartItemList[position]
        if(holder is CartItemViewHolder){

            Glide
                .with(holder.imageView)
                .load(item.image)
                .into(holder.imageView)

            holder.textViewTitle.text=item.title
            holder.textViewPriceIntoQty.text="$CURRENCY ${item.price.roundToInt()} x ${item.quantity}"
            holder.textViewPrice.text="$CURRENCY ${item.price.roundToInt()}"
            holder.textViewQty.text="${item.quantity}"

            holder.buttonDelete.setOnClickListener {
                cartHandler.removeFromCart(item)
                setCartItemList(cartHandler.cartItemList)
                mainViewModel.cartItemCount.postValue(cartHandler.getItemCount())
                mainViewModel.cartItemList.postValue(cartHandler.cartItemList)
            }

            holder.buttonAdd.setOnClickListener {
                item.quantity+=1
                cartHandler.addToCart(item)
                setCartItemList(cartHandler.cartItemList)

                mainViewModel.cartItemCount.postValue(cartHandler.getItemCount())
                mainViewModel.cartItemList.postValue(cartHandler.cartItemList)
            }

            holder.buttonRemove.setOnClickListener {
                if (item.quantity>1){
                    item.quantity-=1
                    cartHandler.addToCart(item)
                }else{
                    cartHandler.removeFromCart(item)
                }
                setCartItemList(cartHandler.cartItemList)
                mainViewModel.cartItemCount.postValue(cartHandler.getItemCount())
                mainViewModel.cartItemList.postValue(cartHandler.cartItemList)
            }

        }
    }

    fun setCartItemList(list:ArrayList<CartItem>){
        this.cartItemList=list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return cartItemList.size
    }
}