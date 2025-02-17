package com.sutonglabs.tracestore.api.request_models

data class CreateOrderRequest(
    val products: List<Product>,
    val totalAmount: String,
    val addressID: Int
)

data class Product(
    val productID: String,
    val quantity: String
)
