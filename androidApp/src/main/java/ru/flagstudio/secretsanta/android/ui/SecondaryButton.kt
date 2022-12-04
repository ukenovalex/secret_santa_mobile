package ru.flagstudio.secretsanta.android.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.flagstudio.secretsanta.android.ui.theme.Colors
import ru.flagstudio.secretsanta.android.ui.theme.Fonts

@Composable
fun SecondaryButton(
    onClick: () -> Unit, title: String,
    width: Dp = 125.dp,
) {
    Button(
        onClick = {
            onClick()
        },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Colors.SecondaryButtonBackground
        ),
        modifier = Modifier
            .padding(12.dp, 8.dp, 12.dp, 9.dp)
            .width(width)
    ) {
        Text(
            text = title,
            fontFamily = Fonts.RobotoBold,
            fontSize = 20.sp,
            color = Colors.SecondaryButtonTextColor,
        )
    }
}