package com.sutonglabs.tracestore.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sutonglabs.tracestore.common.ProductState
import com.sutonglabs.tracestore.common.Resource
import com.sutonglabs.tracestore.use_case.GetProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val productUseCase: GetProductUseCase
): ViewModel() {
    private val _state = mutableStateOf<ProductState>(ProductState())
    val state: State<ProductState> = _state
    init {
        getProduct()
    }
    private fun getProduct() {
        productUseCase().onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = ProductState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = ProductState(product = result.data?.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = ProductState(errorMessage = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}