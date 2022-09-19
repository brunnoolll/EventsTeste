package com.example.sicreditest.commom

sealed class RegisterFormEvent{
    data class nameChanged(val name:String): RegisterFormEvent()
    data class emailChenged(val email:String): RegisterFormEvent()

    object submit : RegisterFormEvent()
}
