package com.sutonglabs.tracestore.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sutonglabs.tracestore.common.Constrains
import com.sutonglabs.tracestore.di.use_case.get_product_detail.GetProductDetailUseCase
import com.sutonglabs.tracestore.viewmodels.state.ProductDetailState
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase,
    stateHandle: SavedStateHandle
): ViewModel() {
    private val _state = mutableStateOf<ProductDetailState>(ProductDetailState())
    val state: State<ProductDetailState> = _state

    init {
        val productId = stateHandle.get<String>(Constrains.PRODUCT_ID_PARAM)
        getProductDetail(productId!!.toInt())
    }

    private fun getProductDetail(productId: Int) {
//        getProductDetailUseCase(productId).onEach { result ->
//            when (result) {
//
//            }
//
//        }
    }
}