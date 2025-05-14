package com.example.pamllazycolumn.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamllazycolumn.data.model.User
import com.example.pamllazycolumn.data.remote.ApiClient
import com.example.pamllazycolumn.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// UserViewModel.kt
class UserViewModel : ViewModel() {
    private val repository = UserRepository(ApiClient.retrofit)

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _selectedUser = MutableStateFlow<User?>(null)
    val selectedUser: StateFlow<User?> = _selectedUser

    init {
        viewModelScope.launch {
            repository.getUsers().collect {
                _users.value = it
            }
        }
    }

    fun loadUser(id: Int) {
        viewModelScope.launch {
            repository.getUser(id).collect {
                _selectedUser.value = it
            }
        }
    }
}
