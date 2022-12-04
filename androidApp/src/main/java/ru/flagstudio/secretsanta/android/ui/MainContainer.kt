package ru.flagstudio.secretsanta.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.flagstudio.secretsanta.android.ui.theme.Colors

@Composable
fun MainContainer(paddingHorizontal: Dp = 0.dp, Child: @Composable () -> Unit) {
    val focusManager = LocalFocusManager.current
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .background(Colors.Background)
            .padding(start = paddingHorizontal, end = paddingHorizontal)
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus(true)
                })
            }
    ) {
        Child()
    }
}