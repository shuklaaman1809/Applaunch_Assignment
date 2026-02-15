package com.example.applaunchdemo.network

import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<UserDto>
}