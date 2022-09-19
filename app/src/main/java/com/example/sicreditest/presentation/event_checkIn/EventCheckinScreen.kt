package com.example.sicreditest.presentation.event_checkIn

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sicreditest.commom.RegisterFormEvent
import com.example.sicreditest.presentation.Screen

@Composable
fun EventCheckinScreen(
    navController: NavController,
    viewModel: EventCheckinViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize()){
        val state = viewModel.state
        val response = viewModel.response.value
        val context = LocalContext.current
        LaunchedEffect(key1 = LocalContext.current){
            viewModel.validationEvents.collect{ event ->
                when(event){
                    is EventCheckinViewModel.ValidationEvent.Sucess -> {
                        viewModel.checkin()
                    }
                }
            }
        }

        if (response.error.isNotBlank()){
            Toast.makeText(context,"Tivemos um problema ao realizar seu check-in, tente novamente mais tarde", Toast.LENGTH_LONG).show()
        }

        if (response.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        if (response.eventCheckin){
            navController.navigate(Screen.EventListScreen.route)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center
        ){

            Text(
                text = "Preencha os dados para realizar login",
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(30.dp))

            TextField(value = state.name, onValueChange = {
                viewModel.onEvent(RegisterFormEvent.nameChanged(it))},
            isError = state.nameError != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Nome")
            })
            if (state.nameError != null){
                Text(
                    text = state.nameError.toString(),
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextField(value = state.email, onValueChange = {
                viewModel.onEvent(RegisterFormEvent.emailChenged(it))},
                isError = state.emailError != null,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Email")
                })
            if (state.emailError != null){
                Text(
                    text = state.emailError.toString(),
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.align(Alignment.End)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.onEvent(RegisterFormEvent.submit)
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Confirmar")
            }
        }
    }
}