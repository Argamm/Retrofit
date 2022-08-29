package com.example.retrofit.api

import com.example.retrofit.dataClass.UsersModel
import retrofit2.Call
import retrofit2.http.GET

interface UsersService {
    @GET("public/v2/users")
    fun getUser(): Call<ArrayList<UsersModel>>
}