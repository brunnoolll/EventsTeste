package com.example.sicreditest.presentation.event_checkIn

import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sicreditest.commom.Constant
import com.example.sicreditest.commom.RegisterFormEvent
import com.example.sicreditest.commom.Resource
import com.example.sicreditest.commom.validator
import com.example.sicreditest.domain.model.EventCheckinRequest
import com.example.sicreditest.domain.use_case.setCheckin.setCheckInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventCheckinViewModel @Inject constructor(
    private val useCase: setCheckInUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(RegistrationFormState())
    private val validator: validator = validator()
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    private val _response = mutableStateOf(EventCheckinState())
    val response : State<EventCheckinState> = _response
    val chave = savedStateHandle.get<String>(Constant.PARAM_EVENT_ID)

    fun onEvent(event: RegisterFormEvent){
        when(event){
            is RegisterFormEvent.emailChenged -> {
                state = state.copy(email = event.email)
            }
            is RegisterFormEvent.nameChanged -> {
                state = state.copy(name = event.name)
            }
            is RegisterFormEvent.submit -> {
                submitData()
            }
        }
    }

    private fun submitData(){
        val emailResult = validator.execute(state.email)
        val nameResult = validator.execute(state.name)

        val hasErrors = listOf(
            emailResult,
            nameResult
        ).any { !it.successful }

        if (hasErrors){
            state = state.copy(
                nameError = nameResult.errorMessage,
                emailError = emailResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Sucess )
        }
    }

    sealed class ValidationEvent {
        object Sucess : ValidationEvent()
    }

    fun checkin(){
        val request = EventCheckinRequest(eventId = chave?: "", email = state.email, name = state.name)
        useCase(request).onEach { result ->
            when(result){
                is Resource.Sucess ->{
                    _response.value = EventCheckinState(eventCheckin = true)
                }
                is Resource.Loading ->{
                    _response.value = EventCheckinState(isLoading = true)
                }
                is Resource.Error ->{
                    _response.value = EventCheckinState(error = result.message?:Constant.ERROR_HTTP)
                }
            }
        }.launchIn(viewModelScope)
    }
}