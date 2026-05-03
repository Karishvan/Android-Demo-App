package com.example.mini_tinder

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel(
    private val apiService: ApiService = RetrofitClient.instance
) : ViewModel() {

    private var allUsers = emptyList<UserData>()
    val currentUser = mutableStateOf<UserData?>(null)
    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)

    private var currentIndex = 0

    init {
        fetchUsers()
    }

    private fun fetchUsers(count: Int = 20) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = apiService.getUsers(count)
                allUsers = response.results
                updateCurrentUser()
            } catch (e: Exception) {
                errorMessage.value = "Failed to load: ${e.localizedMessage}"
            } finally {
                isLoading.value = false
            }
        }
    }

    fun showNextUser() {
        currentIndex = (currentIndex + 1) % allUsers.size
        updateCurrentUser()
    }

    private fun updateCurrentUser() {
        if (allUsers.isNotEmpty()) {
            currentUser.value = allUsers[currentIndex]
        }
    }
}