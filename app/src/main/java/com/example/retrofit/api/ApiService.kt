package com.example.retrofit.api

import com.example.retrofit.dataClass.GetDatas
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/users?page=2")
    fun getInfo(): Call<GetDatas>
}