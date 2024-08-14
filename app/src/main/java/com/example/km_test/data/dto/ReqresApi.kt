package com.example.km_test.data.dto


import retrofit2.http.GET
import retrofit2.http.Query

interface ReqresApi {
    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): UserListDto
}