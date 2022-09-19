package com.example.sicreditest.presentation.event_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sicreditest.commom.Constant
import com.example.sicreditest.commom.Resource
import com.example.sicreditest.domain.use_case.getEvent.getEventDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EventDetailViewModel @Inject constructor(
    private val getDetailsUseCase: getEventDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _event = mutableStateOf(EventDetailState())
    val event : State<EventDetailState> = _event
    val chave = savedStateHandle.get<String>(Constant.PARAM_EVENT_ID)

    init {
        savedStateHandle.get<String>(Constant.PARAM_EVENT_ID)?.let { eventId ->
            getEvent(eventId)
        }
    }

    fun getEvent(eventId : String){
        getDetailsUseCase(eventId).onEach { result ->
            when(result){
                is Resource.Sucess -> {
                    _event.value = EventDetailState(eventDetail = result.data)
                }
                is Resource.Error -> {
                    _event.value = EventDetailState(error = result.message?:Constant.ERROR_HTTP)
                }
                is Resource.Loading -> {
                    _event.value = EventDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}