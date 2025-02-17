package com.sutonglabs.tracestore.api.response_model

data class CreateAddressResponse(
    val status: Boolean,
    val message: String,
    val data: AddressData
)

data class AddressData(
    val id: Int,
    val userID: Int,
    val name: String,
    val phoneNumber: String,
    val pincode: String,
    val city: String,
    val state: String,
    val locality: String,
    val buildingName: String,
    val landmark: String,
    val updatedAt: String,
    val createdAt: String
)
