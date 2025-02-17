package com.sutonglabs.tracestore.repository

import android.content.Context;
import com.sutonglabs.tracestore.api.request_models.CreateAddressRequest
import com.sutonglabs.tracestore.api.request_models.UpdateAddressRequest
import com.sutonglabs.tracestore.api.response_model.AddressData
import com.sutonglabs.tracestore.api.response_model.CreateAddressResponse
import com.sutonglabs.tracestore.api.response_model.UpdateAddressResponse
import com.sutonglabs.tracestore.models.Address;
import com.sutonglabs.tracestore.models.AddressResponse

interface AddressRepository {
    suspend fun getAddress(context: Context): AddressResponse? = null
    suspend fun createAddress(context: Context, address: CreateAddressRequest): CreateAddressResponse? = null
    suspend fun updateAddress(context: Context, updatedAddress: UpdateAddressRequest): UpdateAddressResponse? = null
}
