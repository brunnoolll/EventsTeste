package com.example.sicreditest.presentation.event_detail

import com.example.sicreditest.domain.model.EventDetails

class EventDetailState(
    val isLoading : Boolean = false,
    val eventDetail : EventDetails? = null,
    val error : String = ""
)