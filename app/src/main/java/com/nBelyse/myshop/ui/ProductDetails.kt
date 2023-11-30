package com.nBelyse.myshop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nBelyse.myshop.R
import com.nBelyse.myshop.api.ApiClients
import com.nBelyse.myshop.api.ApiInterface
import com.nBelyse.myshop.databinding.ProductListBinding
import com.nBelyse.myshop.model.Product
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductDetails : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_product_details)
//    }
    class ProductDetails : AppCompatActivity() {
        var productId = -1
        lateinit var binding: ProductListBinding
        lateinit var apiInterface: ApiInterface
        var product: Product? =null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ProductListBinding.inflate(layoutInflater)
            setContentView(binding.root)
            apiInterface = ApiClients.buildClient(ApiInterface::class.java)
            var bundle = intent.extras
            if (bundle!=null){
                productId =bundle.getInt("PRODUCT_ID",-1)

            }
            fetchProductsDetails()
        }


        fun fetchProductsDetails() {
            GlobalScope.launch(Dispatchers.Main) {
                val response = apiInterface.getProductById(productId)

                if (response.isSuccessful) {
                    product = response.body()
                    displayProductDetails()
                } else {
                    Toast.makeText(
                        this@ProductDetails,
                        "Error, cannot fetch product details",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        }
        fun displayProductDetails() {
            product?.let {
                binding.tvTitle.text = it.title
                binding.tvPrice.text = it.price.toString()
//                binding..text = it.rating.toString()
//                binding.tvdescription.text = it.description

                Picasso
                    .get()
                    .load(it.thumbnail)
                    .resize(250, 250)
                    .centerCrop()
                    .into(binding.ivImage)

            }


        }
    }
}