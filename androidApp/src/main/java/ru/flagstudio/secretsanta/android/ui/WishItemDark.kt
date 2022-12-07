package ru.flagstudio.secretsanta.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.flagstudio.secretsanta.android.ui.theme.Colors
import ru.flagstudio.secretsanta.android.ui.theme.Fonts
import ru.flagstudio.secretsanta.android.utils.Constants.MAX_WISH_LENGTH

@Composable
fun WishItemDark(text: String) {
    val content = if (text.length < MAX_WISH_LENGTH) {
        text
    } else {
        "${text.substring(0..MAX_WISH_LENGTH)}..."
    }

    Box(modifier = Modifier
        .clip(RoundedCornerShape(16.dp))
        .background(Colors.Background)
        .padding(
            start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp
        )

    ) {
        Text(
            fontSize = 16.sp,
            text = content.lowercase(),
            fontFamily = Fonts.RobotoMedium,
            color = Colors.TextColor
        )
    }
}