package com.sutonglabs.tracestore.viewmodels.state

import com.sutonglabs.tracestore.models.Product

data class ProductDetailState(
    val isLoading: Boolean = false,
    val productDetail: Product? = null,
    val errorMessage: String = ""
)