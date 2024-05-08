package com.example.retrofitapiintegration.model

data class ProductsData(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)

data class Product(
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String
)


data class APIResponseData(
    val productList: List<Product> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val errorMsg: String = ""
)