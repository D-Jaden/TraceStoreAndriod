package com.sutonglabs.tracestore.di.use_case.get_product_detail

import com.sutonglabs.tracestore.common.Resource
import com.sutonglabs.tracestore.models.Product
import com.sutonglabs.tracestore.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor (private val repository: ProductRepository) {
    operator fun invoke(productId: Int): Flow<Resource<Product>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getProductDetail(productId)
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}