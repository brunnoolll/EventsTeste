package com.example.sicreditest.presentation

sealed class Screen(val route : String){
    object EventListScreen : Screen("event_list_screen")
    object EventDetailScreen : Screen( "event_detail_Screen")
    object EventCheckInScreen : Screen("event_checkin_screen")
}
