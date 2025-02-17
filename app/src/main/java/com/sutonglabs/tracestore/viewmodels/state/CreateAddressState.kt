package com.sutonglabs.tracestore.viewmodels.state

import com.sutonglabs.tracestore.api.response_model.CreateAddressResponse


data class CreateAddressState(
    val isLoading: Boolean = false,
    val address: CreateAddressResponse? = null,
    val errorMessage: String = ""
)
