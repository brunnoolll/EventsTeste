package com.example.sicreditest.data.remote.dto

import com.example.sicreditest.domain.model.EventDetails
import com.google.gson.annotations.SerializedName

data class EventDetailsDto(
    @SerializedName("date")
    val date: Long? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("latitude")
    val latitude: Double? = null,
    @SerializedName("longitude")
    val longitude: Double? = null,
    @SerializedName("people")
    val people: List<Any>? = null,
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("title")
    val title: String? = null
)

fun EventDetailsDto.toDetails() : EventDetails {
    return EventDetails(
        description = description,
        image = image,
        price = price,
        title = title,
        id = id
    )
}
