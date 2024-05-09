package com.example.retrofitapiintegration.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.retrofitapiintegration.R

@Composable
fun NoInternetScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_internet),
            contentDescription = "no_internet_image",
        )
        Text(
            text = "Cat unplugged the internet to charge its energy.\n" +
                    "Give the cat a break, reconnect to the internet!",
            modifier = Modifier.padding(top = 15.dp)
        )
    }
}