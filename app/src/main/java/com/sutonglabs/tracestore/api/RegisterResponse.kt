package com.sutonglabs.tracestore.api

data class RegisterResponse(
    val status: Boolean,
    val data: RegisterResponseData
)

data class RegisterResponseData(
    val user: User,
    val token: String
)
