package com.sutonglabs.tracestore.repository

import com.sutonglabs.tracestore.models.Product

interface ProductRepository {
    suspend fun getProduct(): List<Product>? = null
    suspend fun getProductDetail(id: Int): Product? = null
}