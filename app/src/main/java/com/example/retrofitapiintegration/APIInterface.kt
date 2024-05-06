package com.example.retrofitapiintegration

import com.example.retrofitapiintegration.Modal.ProductsData
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET("products")
    fun getProductData() : Call<ProductsData>
}