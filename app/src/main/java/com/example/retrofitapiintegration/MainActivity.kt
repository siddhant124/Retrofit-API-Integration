package com.example.retrofitapiintegration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitapiintegration.network.ConnectivityObserver
import com.example.retrofitapiintegration.network.NetworkConnectivityObserver
import com.example.retrofitapiintegration.screens.LaunchProductListScreen
import com.example.retrofitapiintegration.screens.NoInternetScreen
import com.example.retrofitapiintegration.ui.theme.RetrofitAPIIntegrationTheme
import com.example.retrofitapiintegration.viewModels.ProductViewModel

class MainActivity : ComponentActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectivityObserver = NetworkConnectivityObserver(applicationContext)

        // Initialize ViewModel
        // val viewModel: ProductViewModel by viewModels()  // Way 1
        val viewModel = ViewModelProvider(this)[ProductViewModel::class.java]  // Way 2

        setContent {
            RetrofitAPIIntegrationTheme {

                val status by connectivityObserver.observer().collectAsState(
                    initial = ConnectivityObserver.Status.UNAVAILABLE
                )

                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    if (status != ConnectivityObserver.Status.AVAILABLE) {
                        NoInternetScreen()
                        return@Surface
                    } else {
                        LaunchedEffect(key1 = true) {
                            viewModel.fetchProductData()
                        }
                        LaunchProductListScreen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}