package com.example.sicreditest.data.remote

import com.example.sicreditest.data.remote.dto.CheckinDto
import com.example.sicreditest.data.remote.dto.EventDetailsDto
import com.example.sicreditest.data.remote.dto.EventDto
import com.example.sicreditest.domain.model.EventCheckinRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventService {
    @GET("events")
    suspend fun getEvent(): List<EventDto>

    @GET("events/{ID}")
    suspend fun getEventById(@Path("ID") eventId : String?) : EventDetailsDto

    @POST("checkin")
    suspend fun checkInEvent(@Body request : EventCheckinRequest) : CheckinDto
}