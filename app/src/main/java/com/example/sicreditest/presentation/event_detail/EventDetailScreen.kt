package com.example.sicreditest.presentation.event_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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

@Composable
fun EventDetailScreen(
    navController: NavController,
    viewModel: EventDetailViewModel = hiltViewModel()
) {

    val state = viewModel.event.value
    val paramsID = viewModel.chave.toString()?:""
    Box(modifier = Modifier.fillMaxSize()) {
        state.eventDetail?.let { event ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)){
                //titulo
                item{
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${event.id}. ${event.title}",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.weight(8f)
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = event.description.toString(),
                        style = MaterialTheme.typography.body2
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "Preço: ${event.price.toString()}",
                        style = MaterialTheme.typography.body2
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    
                    Button(onClick = {
                        navController.navigate(Screen.EventCheckInScreen.route + "/${event.id}")
                    },
                            modifier = Modifier.align(Alignment.Center)) {
                        Text(text = "Check-in" )
                    }

                }
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

                    Button(onClick = {
                        viewModel.getEvent(paramsID)},
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