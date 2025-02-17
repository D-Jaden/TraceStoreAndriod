package com.sutonglabs.tracestore.repository

import android.content.Context
import com.sutonglabs.tracestore.api.request_models.CreateOrderRequest
import com.sutonglabs.tracestore.api.response_model.CreateOrderResponse


interface OrderRepository {
    suspend fun createOrder(context: Context, orderRequest: CreateOrderRequest): CreateOrderResponse? = null
}