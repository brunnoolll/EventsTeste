package com.example.sicreditest.presentation.event_checkIn

import com.example.sicreditest.data.remote.dto.CheckinDto
import com.example.sicreditest.domain.model.EventDetails

data class EventCheckinState (
    val isLoading : Boolean = false,
    val eventCheckin : Boolean = false,
    val error : String = ""
)