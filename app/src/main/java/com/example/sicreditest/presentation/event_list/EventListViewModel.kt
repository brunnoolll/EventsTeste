package com.example.sicreditest.presentation.event_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sicreditest.commom.Constant
import com.example.sicreditest.commom.Resource
import com.example.sicreditest.domain.use_case.getEventList.getEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EventListViewModel @Inject constructor(
    private val getEventsUseCase : getEventUseCase
) : ViewModel() {

    private val _events = mutableStateOf(EventListState())
    val events : State<EventListState> = _events

    init {
        getEvents()
    }

    fun getEvents(){
        getEventsUseCase().onEach { result ->
            when(result){
                is Resource.Sucess -> {
                    _events.value = EventListState(event = result.data?: emptyList())
                }
                is Resource.Loading -> {
                    _events.value = EventListState(isLoading = true)
                }
                is Resource.Error -> {
                    _events.value = EventListState(error = result.message?: Constant.ERROR_HTTP)
                }
            }
        }.launchIn(viewModelScope)
    }


}