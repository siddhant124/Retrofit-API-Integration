package com.example.retrofitapiintegration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitapiintegration.composeUi.LaunchProductListScreen
import com.example.retrofitapiintegration.ui.theme.RetrofitAPIIntegrationTheme
import com.example.retrofitapiintegration.viewModels.ProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewModel
        // One way of Initializing viewModel
        // val viewModel: ProductViewModel by viewModels()
        // Another way of Initializing viewModel
        val viewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        setContent {
            RetrofitAPIIntegrationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LaunchedEffect(key1 = true) {
                        viewModel.fetchProductData()
                    }
                    LaunchProductListScreen(viewModel = viewModel)
                }
            }
        }
    }
}