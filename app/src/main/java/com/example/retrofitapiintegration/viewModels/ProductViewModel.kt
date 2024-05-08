package com.example.retrofitapiintegration.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.retrofitapiintegration.APIInterface
import com.example.retrofitapiintegration.model.APIResponseData
import com.example.retrofitapiintegration.model.ProductsData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductViewModel : ViewModel() {
    private val _productListUiStateFlow = MutableStateFlow(APIResponseData())
    val productListUiStateFlow = _productListUiStateFlow.asStateFlow()


    private val apiInterface: APIInterface by lazy {
        Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIInterface::class.java)
    }

    // Function to fetch product data from the API
    fun fetchProductData() {
        apiInterface.getProductData().enqueue(object : Callback<ProductsData?> {
            override fun onResponse(call: Call<ProductsData?>, response: Response<ProductsData?>) {
                val responseBody = response.body()
                val products = responseBody?.products ?: emptyList()
                _productListUiStateFlow.update {
                    it.copy(
                        productList = products,
                        isLoading = false,
                        isError = false,
                        errorMsg = ""
                    )
                }
            }

            override fun onFailure(call: Call<ProductsData?>, t: Throwable) {
                // Handle failure, For simplicity, log the error
                Log.d("ProductViewModel", "onFailure: ${t.message}")
                _productListUiStateFlow.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                        errorMsg = t.message.toString()
                    )
                }
            }
        })
    }

}