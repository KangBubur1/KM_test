package com.example.km_test.ui.screens.screen3

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.km_test.data.dto.UserListItemDto
import com.example.km_test.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    var isRefreshing = mutableStateOf(false)

    private var _userList = MutableStateFlow<List<UserListItemDto>>(emptyList())
    val userList = _userList.asStateFlow()

    private var _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    var curPage = 1
    private val perPage = 10

    fun fetchUser() {
        viewModelScope.launch {
            if (_isLoading.value) return@launch


            _isLoading.value = true
            try {
                val userListResponse = userRepository.fetchUsers(curPage, perPage)
                _userList.value += userListResponse.data
                curPage++
                Log.d("UserListViewModel", "Fetched users: ${userListResponse.data}")
            } catch (e: Exception) {
                Log.e("UserListViewModel", "Error fetching users: ${e.message}")
            } finally {
                _isLoading.value = false
                if (isRefreshing.value) {
                    isRefreshing.value = false
                }
            }
        }
    }

    fun resetPagination() {
        curPage = 1
        _userList.value = emptyList()
    }
}