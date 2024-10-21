package com.sutonglabs.tracestore.use_case

import com.sutonglabs.tracestore.common.Resource
import com.sutonglabs.tracestore.models.Product
import com.sutonglabs.tracestore.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(): Flow<Resource<List<Product>>> = flow {
        try{
            emit(Resource.Loading())
            val products = productRepository.getProduct()?. map { it }
            emit(Resource.Success(data = products))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

}