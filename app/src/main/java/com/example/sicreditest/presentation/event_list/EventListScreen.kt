package com.example.sicreditest.presentation.event_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sicreditest.presentation.Screen
import com.example.sicreditest.presentation.event_list.components.EventListItem

@Composable
fun EventListScreen(
    navController: NavController,
    viewmodel : EventListViewModel = hiltViewModel()
) {

    val state = viewmodel.events.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(state.event){ event ->
                EventListItem(event = event,
                    onItemClick = {
                        navController.navigate(Screen.EventDetailScreen.route + "/${event.id}")
                    })
            }
        }
        if (state.error.isNotBlank()){
            LazyColumn(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                item {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Button(onClick = { viewmodel.getEvents() },
                        modifier = Modifier.align(Alignment.Center)) {
                        Text(text = "Tentar novamente" )
                    }
                }
            }
        }
        if (state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}