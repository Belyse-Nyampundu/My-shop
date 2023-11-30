package com.nBelyse.myshop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nBelyse.myshop.api.ApiClients
import com.nBelyse.myshop.api.ApiInterface
import com.nBelyse.myshop.model.Product
import com.nBelyse.myshop.model.ProductsResponse
import com.nBelyse.myshop.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response


class ProductsViewModel:ViewModel() {
    val productsRepo = ProductRepository()
    val productsLiveData = MutableLiveData<List<Product>>()
    val  errLiveData = MutableLiveData<String>()

    fun fetchProducts() {
        viewModelScope.launch {
            val response = productsRepo.getProducts()
            if (response.isSuccessful) {
                productsLiveData.postValue(response.body()?.products)
            } else {
                errLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

//    fun fetchProducts(){




//        fun fetchProducts(){
//            viewModelScope.launch {
//                var response= productRepo.getProducts()
//                if (response.isSuccessful){
//                    productsLiveData.postValue((response.body()?.products))
//                }else{
//                    errLiveData.postValue((response.errorBody()?.string()))
//                }
//            }
//        }


    }






