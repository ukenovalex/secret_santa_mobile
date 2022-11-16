package ru.flagstudio.secretsanta.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greeting()
        }
    }
}

@Composable
fun Greeting() {
    var email: String = ""
    var password: String = ""
    Column {
        TextField(value = email, onValueChange = {
            email = it
        })
        TextField(value = password, onValueChange = {
            password = it
        })
    }
}
