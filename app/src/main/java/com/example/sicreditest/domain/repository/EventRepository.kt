package com.example.sicreditest.domain.repository

import com.example.sicreditest.data.remote.dto.CheckinDto
import com.example.sicreditest.data.remote.dto.EventDetailsDto
import com.example.sicreditest.data.remote.dto.EventDto
import com.example.sicreditest.domain.model.EventCheckinRequest

interface EventRepository {
    suspend fun getEvent() : List<EventDto>

    suspend fun getEventById(eventId : String?) : EventDetailsDto

    suspend fun checkInEvent(request : EventCheckinRequest) : CheckinDto
}