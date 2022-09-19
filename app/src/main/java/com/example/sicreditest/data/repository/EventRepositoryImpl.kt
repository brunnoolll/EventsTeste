package com.example.sicreditest.data.repository

import com.example.sicreditest.data.remote.EventService
import com.example.sicreditest.data.remote.dto.CheckinDto
import com.example.sicreditest.data.remote.dto.EventDetailsDto
import com.example.sicreditest.data.remote.dto.EventDto
import com.example.sicreditest.domain.model.EventCheckinRequest
import com.example.sicreditest.domain.repository.EventRepository
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val api : EventService
) : EventRepository {
    override suspend fun getEvent(): List<EventDto> {
        return api.getEvent()
    }

    override suspend fun getEventById(eventId : String?): EventDetailsDto {
        return api.getEventById(eventId)
    }

    override suspend fun checkInEvent(request: EventCheckinRequest): CheckinDto {
        return api.checkInEvent(request)
    }
}