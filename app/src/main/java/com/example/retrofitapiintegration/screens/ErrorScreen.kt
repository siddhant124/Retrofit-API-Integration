package com.example.retrofitapiintegration.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ErrorScreen(
    errorMessage: String,
//    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Oops! Something Went Wrong",
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = errorMessage,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(32.dp))
//        Button(
//            onClick = { onRetry() }
//        ) {
//            Text(text = "Retry")
//        }
    }
}