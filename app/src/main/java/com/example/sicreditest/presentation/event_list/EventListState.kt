package com.example.sicreditest.presentation.event_list

import com.example.sicreditest.domain.model.EventItem

class EventListState(
    val isLoading : Boolean = false,
    val event : List<EventItem> = emptyList(),
    val error : String = ""
)