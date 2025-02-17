package com.sutonglabs.tracestore.api.response_model

data class CreateOrderResponse(
    val products: List<Product>,
    val totalAmount: String,
    val addressID: Int
)

data class Product(
    val productID: String,
    val quantity: String
)

data class OrderResponse(
    val message: String,
    val order: Order,
    val orderItems: List<OrderItem>
)

data class Order(
    val status: String,
    val id: Int,
    val userID: Int,
    val totalAmount: String,
    val addressID: Int,
    val updatedAt: String,
    val createdAt: String
)

data class OrderItem(
    val id: Int,
    val orderID: Int,
    val productID: String,
    val quantity: String,
    val updatedAt: String,
    val createdAt: String
)
