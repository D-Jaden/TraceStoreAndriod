package com.sutonglabs.tracestore.api.request_models

data class UpdateAddressRequest(
    val addressId: Int,
    val name: String,
    val phoneNumber: String,
    val pincode: String,
    val city: String,
    val state: String,
    val locality: String,
    val buildingName: String,
    val landmark: String
)