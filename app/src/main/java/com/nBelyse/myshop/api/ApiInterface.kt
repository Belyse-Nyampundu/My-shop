package com.nBelyse.myshop.api

import com.nBelyse.myshop.model.Product
import com.nBelyse.myshop.model.ProductsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @GET ("/product")
    fun getProduct(): Call<ProductsResponse>

    @GET("/product/{id")
    fun getProductById(@Path("id")productId:Int):Call<Product>

    @POST("/product")
    fun postProduct(@Body product: Product):Call<Product>
}