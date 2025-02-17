package com.sutonglabs.tracestore.use_case

import android.content.Context
import com.sutonglabs.tracestore.api.request_models.CreateAddressRequest
import com.sutonglabs.tracestore.api.response_model.CreateAddressResponse
import com.sutonglabs.tracestore.common.Resource
import com.sutonglabs.tracestore.models.AddressResponse
import com.sutonglabs.tracestore.repository.AddressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreateAddressUseCase @Inject constructor(
    private val repository: AddressRepository,
    private val context: Context,
){
    operator fun invoke(addressRequest: CreateAddressRequest): Flow<Resource<CreateAddressResponse>> = flow {
        try {
            emit(Resource.Loading())
            val address = repository.createAddress(context, address = addressRequest)
            emit(Resource.Success(address))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}