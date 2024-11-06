package com.sutonglabs.tracestore.common

import com.sutonglabs.tracestore.models.Product
import com.sutonglabs.tracestore.models.ProductResponse

data class ProductState(
    val isLoading: Boolean = false,
    val product: List<Product>? = null,
    val errorMessage: String = ""
)
