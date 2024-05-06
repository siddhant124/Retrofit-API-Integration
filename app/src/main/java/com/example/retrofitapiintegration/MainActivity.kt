package com.example.retrofitapiintegration

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.retrofitapiintegration.Modal.Product
import com.example.retrofitapiintegration.Modal.ProductsData
import com.example.retrofitapiintegration.ui.theme.RetrofitAPIIntegrationTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RetrofitAPIIntegrationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    var productList by remember { mutableStateOf<List<Product>>(emptyList()) }
                    val isLoading = remember { mutableStateOf(true) }
                    val isError = remember { mutableStateOf(false) }
                    val errorMsg = remember { mutableStateOf("") }

                    val retrofitBuilder = Retrofit.Builder()
                        .baseUrl("https://dummyjson.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(APIInterface::class.java)

                    val retrofitData = retrofitBuilder.getProductData()

                    retrofitData.enqueue(object : Callback<ProductsData?> {
                        override fun onResponse(
                            call: Call<ProductsData?>,
                            response: Response<ProductsData?>
                        ) {
                            val responseBody = response.body()
                            val products = responseBody?.products ?: emptyList()
                            productList = products
                            isLoading.value = false
                            isError.value = false
                            errorMsg.value = "dgerger"
                        }

                        override fun onFailure(call: Call<ProductsData?>, t: Throwable) {
                            Log.d("ProductViewModel", "onFailure: ${t.message}")
                            isLoading.value = false
                            isError.value = true
                            errorMsg.value = t.message.toString()
                        }
                    })


                    ProductList(
                        productList = productList,
                        isLoading = isLoading.value,
                        isError = isError.value,
                        errorMsg = errorMsg.value
                    )
                }
            }
        }
    }
}


// Composable function to display the product list
@Composable
fun ProductList(
    productList: List<Product>,
    isLoading: Boolean,
    isError: Boolean,
    errorMsg: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column {
            Text("Products:")
            if (isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(48.dp)
                    )
                    Text(text = "Loading...", modifier = Modifier.padding(top = 15.dp))
                }
            } else {
                if (isError) {
                    Text(text = "Error While Loading response\n${errorMsg}")
                } else {
                    productList.forEach { product ->
                        Text(text = product.brand)
                    }
                }
            }
        }
    }
}