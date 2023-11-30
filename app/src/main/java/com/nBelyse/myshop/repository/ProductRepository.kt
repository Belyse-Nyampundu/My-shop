package com.nBelyse.myshop.repository

import com.nBelyse.myshop.api.ApiClients
import com.nBelyse.myshop.api.ApiInterface
import com.nBelyse.myshop.model.Product
import com.nBelyse.myshop.model.ProductsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response


class ProductRepository {
    private val apiClient = ApiClients.buildClient(ApiInterface::class.java)

    suspend fun getProducts() {
        return withContext(Dispatchers.IO) {
            apiClient.getProduct()
        }
    }
}
