package com.example.retrofitapiintegration.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.retrofitapiintegration.viewModels.ProductViewModel

// Composable function to display the product list
@Composable
fun LaunchProductListScreen(
    viewModel: ProductViewModel
) {
    val productsDataUiStata = viewModel.productListUiStateFlow.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column {
            Text("Products:")
            if (productsDataUiStata.isLoading) {
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
                if (productsDataUiStata.isError) {
                    Text(text = "Error While Loading response\n${productsDataUiStata.errorMsg}")
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                    ) {
                        productsDataUiStata.productList.forEach { product ->
                            Text(text = product.brand)
                        }
                    }
                }
            }
        }
    }
}