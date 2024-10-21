package com.sutonglabs.tracestore.repository

import com.sutonglabs.tracestore.data.DemoDB
import com.sutonglabs.tracestore.models.Product
import javax.inject.Inject

class ProductRepositoryImp @Inject constructor(
    private val demoDB: DemoDB
) : ProductRepository {
    override suspend fun getProduct(): List<Product> {
        return demoDB.getProduct()
    }
    override suspend fun getProductDetail(id: Int): Product {
        return demoDB.getProduct()[id-1]
    }
}