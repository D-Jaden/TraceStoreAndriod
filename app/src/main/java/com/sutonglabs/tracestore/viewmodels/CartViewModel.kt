package com.sutonglabs.tracestore.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sutonglabs.tracestore.api.TraceStoreAPI
import com.sutonglabs.tracestore.api.UpdateCartRequest
import com.sutonglabs.tracestore.common.CartProductState
import com.sutonglabs.tracestore.common.Resource
import com.sutonglabs.tracestore.data.getJwtToken
import com.sutonglabs.tracestore.models.CartItem
import com.sutonglabs.tracestore.models.CartProduct
import com.sutonglabs.tracestore.repository.CartRepository
import com.sutonglabs.tracestore.use_case.GetCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CartViewModel  @Inject constructor(
    private val cartUseCase: GetCartUseCase,
    private val cartRepository: CartRepository,
    private val cartApiService: TraceStoreAPI
    ): ViewModel() {
        private val _state = mutableStateOf(CartProductState())
        val state: State<CartProductState> = _state

        private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
        val cartItems: StateFlow<List<CartItem>> = _cartItems

    init {
        getProduct()
    }
    private fun getProduct() {
        cartUseCase().onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = CartProductState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = CartProductState(product = result.data?.data?.cartItems ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = CartProductState(errorMessage = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

//    fun updateCartItem(cartItemId: Int, newQuantity: Int) {
//        viewModelScope.launch {
//            try {
//                val response = cartApiService.updateCartItem("", UpdateCartRequest(cartItemId, newQuantity))
//                if (response.isSuccessful && response.body()?.status == true) {
//                    val updatedCartItem = response.body()?.data
//                    _cartItems.value = _cartItems.value.map { item ->
//                        if (item.id == cartItemId) item.copy(quantity = updatedCartItem?.quantity ?: item.quantity)
//                        else item
//                    }
//                }
//            } catch (e: Exception) {
//                Log.e("CartViewModel", "Error updating cart: ${e.message}")
//            }
//        }
//    }

    fun updateCartItem(cartItemId: Int, newQuantity: Int) {
        viewModelScope.launch {
            try {
                val response = cartApiService.updateCartItem("", UpdateCartRequest(cartItemId, newQuantity))
                if (response.isSuccessful && response.body()?.status == true) {
                    val updatedCartItem = response.body()?.data
                    _cartItems.value = _cartItems.value.map { item ->
                        if (item.id == cartItemId) item.copy(quantity = updatedCartItem?.cartItems?.firstOrNull()?.quantity ?: item.quantity) // TODO: Fix  the quantity not being updated
                        else item
                    }
                }
            } catch (e: Exception) {
                Log.e("CartViewModel", "Error updating cart: ${e.message}")
            }
        }
    }
}