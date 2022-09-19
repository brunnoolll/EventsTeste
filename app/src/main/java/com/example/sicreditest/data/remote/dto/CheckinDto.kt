package com.example.sicreditest.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CheckinDto(
    @SerializedName("code")
    val code : String? = null
)