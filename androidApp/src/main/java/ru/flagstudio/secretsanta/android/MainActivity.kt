package ru.flagstudio.secretsanta.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import auth.AuthViewModel
import dev.icerock.moko.mvvm.flow.compose.observeAsActions
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greeting()
        }
    }
}

@Composable
fun Greeting(
    viewModel: AuthViewModel = viewModel()
) {
    val email: String by viewModel.email.collectAsState()
    val password: String by viewModel.password.collectAsState()

    viewModel.actions.observeAsActions {}

    Column {
        TextField(value = email, onValueChange = {
            viewModel.email.value = it
        })
        TextField(value = password, onValueChange = {
            viewModel.password.value = it
        })
        Button(onClick = {
            viewModel.pressLogin()
        }) {}
    }
}
