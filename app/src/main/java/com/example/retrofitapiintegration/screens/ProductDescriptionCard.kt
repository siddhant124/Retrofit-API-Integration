package com.example.retrofitapiintegration.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.retrofitapiintegration.R


@Preview(showBackground = true)
@Composable
fun ProductDescriptionCard(
    productThumbnailUrl: String = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
    productName: String = "iPhone 14 pro max",
    productDescription: String = "Apple company ka sabse kam bikhne wala phone",
    productBrand: String = "Apple",
    productPrice: Int = 456,
    productRating: Double = 3.5,
    productCategory: String = "Smartphones",
) {

    var isImageLoading by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(25.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(productThumbnailUrl)
                    .build(),
                contentDescription = "productThumbnail",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color.White),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.no_internet),
                onLoading = { isImageLoading = true },
                onSuccess = { isImageLoading = false }
            )

            Text(
                text = productName,
                style = typography.headlineSmall, // Use appropriate text style
                modifier = Modifier.padding(bottom = 4.dp) // Add padding below the text
            )
            Text(
                text = productDescription,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                style = typography.bodyMedium, // Use appropriate text style
                modifier = Modifier.padding(bottom = 4.dp) // Add padding below the text
            )
            Text(
                text = "Brand" + productBrand,
                style = typography.bodyMedium, // Use appropriate text style
                modifier = Modifier.padding(bottom = 4.dp) // Add padding below the text
            )
            Text(
                text = "Price: $" + productPrice.toString(),
                style = typography.bodyMedium, // Use appropriate text style
                modifier = Modifier.padding(bottom = 4.dp) // Add padding below the text
            )
            Text(
                text = "Rating: " + productRating.toString() + "/5",
                style = typography.bodyMedium, // Use appropriate text style
                modifier = Modifier.padding(bottom = 4.dp) // Add padding below the text
            )
            Text(
                text = "Category: " + productCategory,
                style = typography.bodyMedium, // Use appropriate text style
                modifier = Modifier.padding(bottom = 4.dp) // Add padding below the text
            )
        }
    }
}