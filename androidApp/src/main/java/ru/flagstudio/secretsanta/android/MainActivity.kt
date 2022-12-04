package ru.flagstudio.secretsanta.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import ru.flagstudio.secretsanta.android.navigation.Navigation
import ru.flagstudio.secretsanta.android.ui.MainVideoContainer
import ru.flagstudio.secretsanta.android.ui.theme.Colors


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Colors.Background
            ) {
                MainVideoContainer {
                    Navigation()
                }
            }
        }
    }
}
