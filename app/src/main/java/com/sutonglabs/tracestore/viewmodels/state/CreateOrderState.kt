package com.sutonglabs.tracestore.viewmodels.state

import com.sutonglabs.tracestore.api.response_model.CreateOrderResponse

data class CreateOrderState(
    val isLoading: Boolean = false,
    val address: CreateOrderResponse? = null,
    val errorMessage: String = ""
)
