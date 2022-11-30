package ru.flagstudio.secretsanta.android.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import ru.flagstudio.secretsanta.android.ui.theme.Colors
import ru.flagstudio.secretsanta.android.ui.theme.Fonts


@Composable
fun AppTitle(text: String) {
    Text(
        text = text,
        fontFamily = Fonts.InriaSansBold,
        fontSize = 32.sp,
        color = Colors.TextColor,
        textAlign = TextAlign.Center
    )
}