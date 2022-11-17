package ru.flagstudio.secretsanta.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import auth.AuthEvent
import auth.AuthViewModel
import com.adeo.kviewmodel.compose.observeAsState


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authViewModel = AuthViewModel()
        setContent {
            Greeting(authViewModel)
        }
    }
}

@Composable
fun Greeting(viewModel: AuthViewModel) {
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
    }
}
