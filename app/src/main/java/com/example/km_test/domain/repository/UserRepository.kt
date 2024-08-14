package com.example.km_test.domain.repository

import com.example.km_test.data.dto.ReqresApi
import com.example.km_test.data.dto.UserListDto
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val reqresService: ReqresApi
) {
    suspend fun fetchUsers(page: Int, perPage: Int): UserListDto {
        return reqresService.getUsers(page, perPage)
    }
}