package com.nBelyse.myshop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.nBelyse.myshop.api.ApiClients
import com.nBelyse.myshop.api.ApiInterface
import com.nBelyse.myshop.databinding.ActivityMainBinding
import com.nBelyse.myshop.model.Product
import com.nBelyse.myshop.model.ProductsResponse
import com.nBelyse.myshop.ProductAdapter


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit  var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        fetchProduct()
    }
    fun fetchProduct(){
        var apiClient = ApiClients.buildClient(ApiInterface::class.java)
        var request = apiClient.getProduct()
        request.enqueue(object : Callback<ProductsResponse> {

            override fun onResponse(
                call: Call<ProductsResponse>,
                response: Response<ProductsResponse>
            ) {
                if (response.isSuccessful) {
                    var products = response.body()?.products?: emptyList<Product>()
                    binding.rvProduct.layoutManager =GridLayoutManager(this@MainActivity,2)

                    binding.rvProduct.adapter = ProductAdapter(products)
                    Toast.makeText(
                        baseContext,
                        "fetched ${products?.size} products", Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(baseContext, response.errorBody()?.string(), Toast.LENGTH_LONG)
                        .show()
                }
            }


            override fun onFailure(call: Call<ProductsResponse>, t:Throwable){
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}









//package com.example.myshop
//
//import android.annotation.SuppressLint
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Toast
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.nBelyse.myshop.api.ApiClients
//import com.nBelyse.myshop.api.ApiInterface
//import com.nBelyse.myshop.model.Product
//import com.nBelyse.myshop.model.ProductsResponse
//import com.nBelyse.myshop.R
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//
//
//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//        fetchProduct()
//    }
//
//    fun fetchProduct(){
//        var apiClient = ApiClients.buildClient(ApiInterface::class.java)
//        var request = apiClient.getProduct()
//        request.enqueue(object : Callback<ProductsResponse>{
//            override fun onResponse(call: Call<ProductsResponse>, response: Response<ProductsResponse>) {
//                if (response.isSuccessful){
//                    var products = response.body()?.products
//
//                    Toast.makeText(baseContext,
//                    "fetched ${products?.size}products",Toast.LENGTH_SHORT).show()
//
//
//
//                }
//                else{
//                    Toast.makeText(baseContext, response.errorBody()?.toString(), Toast.LENGTH_LONG).show()
//                }
//
//            }
//
//            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
//                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
//            }
//        })
//    }
//}