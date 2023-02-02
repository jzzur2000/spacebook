package com.example.spacebook.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.http.Body
import retrofit2.http.POST
import java.time.Instant

interface SpacebookApi {
    @JsonClass(generateAdapter = true)
    data class ApiError(val type: String)

    @JsonClass(generateAdapter = true)
    data class ApiResponse<T>(
        val status: String,
        val data: T?,
        val error: ApiError?
    )

    @JsonClass(generateAdapter = true)
    data class User(
        @Json(name = "id") val id: Int,
        @Json(name = "email") val email: String,
        @Json(name = "name") val name: String,
        @Json(name = "registeredAt") val registeredAt: Instant,
        @Json(name = "githubUsername") val githubUsername: String?,
        @Json(name = "rating") val rating: Double?,
    )

    @JsonClass(generateAdapter = true)
    data class SessionRequest(val email: String, val password: String)

    @JsonClass(generateAdapter = true)
    data class UserRequest(val name: String, val email: String, val password: String, val githubUsername: String)

    @POST("session")
    suspend fun login(@Body request: SessionRequest): ApiResponse<User>

    @POST("users")
    suspend fun createAccount(@Body request: UserRequest)
}
