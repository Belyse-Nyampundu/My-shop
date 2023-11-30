package com.nBelyse.myshop.model

import com.nBelyse.myshop.model.Product

data class ProductsResponse(
    var products:List<Product>,
    var total:Int,
    var limit:Int,
    var skip:Int
)
