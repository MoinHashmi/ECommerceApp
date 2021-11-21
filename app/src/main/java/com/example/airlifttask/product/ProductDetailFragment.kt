package com.example.airlifttask.product

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.airlifttask.R
import com.example.airlifttask.cart.handler.CartHandler
import com.example.airlifttask.cart.model.CartItem
import com.example.airlifttask.cart.viewModel.MainActivityViewModel
import com.example.airlifttask.home.model.Product
import com.example.airlifttask.utils.Constants.CURRENCY
import kotlin.math.roundToInt

class ProductDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ProductDetailFragment()
    }

    private lateinit var viewModel: ProductDetailViewModel
    private lateinit var imageView: ImageView
    private lateinit var textViewTitle: TextView
    private lateinit var textViewPrice: TextView
    private lateinit var textViewToolbarTitle: TextView
    private lateinit var textViewDescription: TextView
    private lateinit var buttonBack: LinearLayout
    private lateinit var buttonAddToCart: Button
    private lateinit var product:Product
    private val cartHandler=CartHandler.INSTANCE
    private lateinit var mainViewModel:MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductDetailViewModel::class.java)
        product=arguments?.get("prod") as Product


        activity?.let {
            mainViewModel= ViewModelProviders.of(it)[MainActivityViewModel::class.java]
        }

        initUI(view)
        setupData(view)

    }

    private fun initUI(view: View) {
        buttonBack=view.findViewById(R.id.buttonBack)
        imageView=view.findViewById(R.id.imageView)
        textViewTitle=view.findViewById(R.id.textViewTitle)
        textViewPrice=view.findViewById(R.id.textViewPrice)
        textViewToolbarTitle=view.findViewById(R.id.textViewToolbarTitle)
        textViewDescription=view.findViewById(R.id.textViewDescription)
        buttonAddToCart=view.findViewById(R.id.buttonAddToCart)


        buttonAddToCart.setOnClickListener {
            val item= CartItem(product.id,product.title,product.price,product.image,1)
            if(!cartHandler.hasItem(item)) {
                cartHandler.addToCart(item)
                mainViewModel.persistCartData(it.context, item)
                mainViewModel.cartItemCount.postValue(cartHandler.getItemCount())
                mainViewModel.cartItemList.postValue(cartHandler.cartItemList)
            }
        }

        buttonBack.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }

    }

    private fun setupData(view: View) {

        Glide
            .with(view)
            .load(product.image)
            .into(imageView)

        textViewTitle.text=product.title
        textViewDescription.text=product.description
        textViewPrice.text="$CURRENCY ${product.price.roundToInt()}"

    }

}