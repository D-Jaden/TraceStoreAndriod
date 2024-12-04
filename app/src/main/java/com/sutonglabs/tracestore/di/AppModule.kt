package com.sutonglabs.tracestore.di

import com.sutonglabs.tracestore.api.TraceStoreAPI
import com.sutonglabs.tracestore.repository.ProductRepository
import com.sutonglabs.tracestore.repository.ProductRepositoryImp
import com.sutonglabs.tracestore.usecases.GetProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.example.com/") // Replace with your API base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideTraceStoreAPI(retrofit: Retrofit): TraceStoreAPI {
        return retrofit.create(TraceStoreAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(api: TraceStoreAPI): ProductRepository {
        return ProductRepositoryImp(api)
    }

    @Provides
    @Singleton
    fun provideGetProductUseCase(repository: ProductRepository): GetProductUseCase {
        return GetProductUseCase(repository)
    }
}
