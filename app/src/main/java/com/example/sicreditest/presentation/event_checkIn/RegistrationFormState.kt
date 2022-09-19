package com.example.sicreditest.presentation.event_checkIn

data class RegistrationFormState(
    val name : String = "",
    val nameError : String? = null,
    val email: String = "",
    val emailError : String? = null
)
