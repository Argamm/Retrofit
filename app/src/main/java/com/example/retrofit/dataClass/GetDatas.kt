package com.example.retrofit.dataClass

import com.google.gson.annotations.SerializedName

data class GetDatas(
    @SerializedName("data")
    val data: ArrayList<ModelInfo>
)