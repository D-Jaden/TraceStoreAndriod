package com.sutonglabs.tracestore.repository

import com.sutonglabs.tracestore.models.Product
import com.sutonglabs.tracestore.models.ProductResponse
import retrofit2.Call

interface ProductRepository {
    suspend fun getProduct(): ProductResponse? = null
    suspend fun getProductDetail(id: Int): Product? = null
}