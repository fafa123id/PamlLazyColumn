package com.example.pamllazycolumn.data.remote

import com.example.pamllazycolumn.data.model.User
import retrofit2.http.*

// ApiService.kt
interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): User
}
