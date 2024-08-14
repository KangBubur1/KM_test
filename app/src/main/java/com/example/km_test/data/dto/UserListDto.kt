package com.example.km_test.data.dto

data class UserListDto (
    val data: List<UserListItemDto>
)

data class UserListItemDto (
    val id: Int,
    val first_name: String,
    val last_name: String,
    val email: String,
    val avatar: String
)