package com.sutonglabs.tracestore.di

import com.sutonglabs.tracestore.api.TraceStoreAPI
import com.sutonglabs.tracestore.data.DemoDB
import com.sutonglabs.tracestore.repository.ProductRepository
import com.sutonglabs.tracestore.repository.ProductRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideProductRepository(traceStoreAPIService: TraceStoreAPI): ProductRepository {
        return ProductRepositoryImp(traceStoreAPIService)
    }
}