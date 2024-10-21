package com.sutonglabs.tracestore.repository

import android.content.Context
import com.sutonglabs.tracestore.api.LoginRequest
import com.sutonglabs.tracestore.api.RegisterRequest
import com.sutonglabs.tracestore.api.TraceStoreAPI
import com.sutonglabs.tracestore.data.getJwtToken
import com.sutonglabs.tracestore.data.saveJwtToken
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val apiService: TraceStoreAPI,
    private val context: Context
) {
    val jwtToken: Flow<String?> = getJwtToken(context)

    suspend fun login(username: String, password: String): Result<String> {
        val response = apiService.login(LoginRequest(username, password))
        return if (response.isSuccessful && response.body() != null) {
            val jwt = response.body()!!.data.token
            saveJwtToken(context, jwt)
            Result.success(jwt)
        } else {
            Result.failure(Exception("Login failed"))
        }
    }

    suspend fun register(username: String,
                         email: String,
                         firstName: String,
                         lastName: String,
                         age: String,
                         GSTIN: String,
                         password: String
                         ): Result<String> {
        val response = apiService.register(RegisterRequest(username, email, firstName, lastName, age, GSTIN, password))
        return if (response.isSuccessful && response.body() != null) {
            val jwt = response.body()!!.data.token
            saveJwtToken(context, jwt)
            Result.success(jwt)
        } else {
            Result.failure(Exception("Registration Failed!"))
        }

    }
}