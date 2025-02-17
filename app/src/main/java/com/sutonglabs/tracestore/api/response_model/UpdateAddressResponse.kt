package com.sutonglabs.tracestore.api.response_model

data class UpdateAddressResponse(
    val status: Boolean,
    val message: String,
    val data: AddressData
)