package com.example.pamllazycolumn.repository

import com.example.pamllazycolumn.data.model.User
import com.example.pamllazycolumn.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// UserRepository.kt
class UserRepository(private val api: ApiService) {
    fun getUsers(): Flow<List<User>> = flow {
        emit(api.getUsers())
    }

    fun getUser(id: Int): Flow<User> = flow {
        emit(api.getUser(id))
    }
}
