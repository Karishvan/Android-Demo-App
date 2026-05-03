package com.example.mini_tinder

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/")
    suspend fun getUsers(
        @Query("results") count: Int
    ): UserResponse
}