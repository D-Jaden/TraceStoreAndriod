package com.sutonglabs.tracestore.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sutonglabs.tracestore.api.request_models.CreateAddressRequest
import com.sutonglabs.tracestore.api.request_models.CreateOrderRequest
import com.sutonglabs.tracestore.repository.OrderRepository
import com.sutonglabs.tracestore.viewmodels.state.AddressState
import com.sutonglabs.tracestore.viewmodels.state.CreateOrderState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRepository: OrderRepository
): ViewModel() {

    private val _state = mutableStateOf(CreateOrderState())
    val state: State<CreateOrderState> = _state

    fun createOrder(orderRequest: CreateOrderRequest, context: Context) {
        viewModelScope.launch {
            try {
                orderRepository.createOrder(context, orderRequest)
                Log.d("OrderViewModel", "Order created successfully")

                // Show Toast after successfully adding the product
                Toast.makeText(context, "Order Created!", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.e("OrderViewModel", "Error creating Order: ${e.message}")
            }
        }
    }

    fun addressOrder(orderRequest: CreateOrderRequest, context: Context) {
        viewModelScope.launch {
            try {
                orderRepository.createOrder(context, orderRequest)
                Log.d("OrderViewModel", "Order created successfully")

                // Show Toast after successfully adding the product
                Toast.makeText(context, "Order Created!", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.e("OrderViewModel", "Error creating Order: ${e.message}")
            }
        }
    }
}