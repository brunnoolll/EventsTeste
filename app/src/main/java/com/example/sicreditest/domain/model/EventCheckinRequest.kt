package com.example.sicreditest.domain.model

import com.google.gson.annotations.SerializedName

data class EventCheckinRequest(
    @SerializedName("eventId")
    var eventId : String? = null,
    @SerializedName("name")
    var name : String? = null,
    @SerializedName("email")
    var email : String? = null
)
