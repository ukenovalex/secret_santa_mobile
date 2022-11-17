package ru.flagstudio.secretsanta.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import auth.AuthEvent
import auth.AuthViewModel
import com.adeo.kviewmodel.compose.observeAsState

@Composable
fun AuthScreen(viewModel: AuthViewModel, onNavigateToTest: () -> Unit) {
    val state = viewModel.viewStates().observeAsState()
    Column {
        Text(text = "Authorization")

        TextField(value = state.value.email, onValueChange = {
            viewModel.obtainEvent(AuthEvent.InputEmail(it))
        })
        TextField(value = state.value.password, onValueChange = {
            viewModel.obtainEvent(AuthEvent.InputPassword(it))
        })
        Button(onClick = { viewModel.obtainEvent(AuthEvent.PressLogin) }) {}
        Button(onClick = {
            onNavigateToTest()
        }) {}
    }
}