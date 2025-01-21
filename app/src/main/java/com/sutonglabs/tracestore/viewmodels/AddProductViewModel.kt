package com.sutonglabs.tracestore.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sutonglabs.tracestore.models.Product
import com.sutonglabs.tracestore.repository.ProductRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class AddProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

    // The result of the product addition status (success or failure)
    private val _addProductStatus = mutableStateOf<Boolean?>(null)
    val addProductStatus: State<Boolean?> = _addProductStatus

    // Add product method
    fun addProduct(product: Product) {
        viewModelScope.launch {
            val result = productRepository.addProduct(product)
            _addProductStatus.value = result != null
        }
    }
}
