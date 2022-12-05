package ru.flagstudio.secretsanta.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ru.flagstudio.secretsanta.android.R
import ru.flagstudio.secretsanta.android.ui.theme.Colors
import ru.flagstudio.secretsanta.android.ui.theme.Fonts


@Composable
fun AppDialogError(isShow: Boolean, message: String, onHide: () -> Unit) {
    if (isShow) {
        Dialog(onDismissRequest = onHide) {
            Card(
                backgroundColor = Colors.SecondaryBackground,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 12.dp, end = 12.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.santa_error),
                        contentDescription = "Santa Error",
                        modifier = Modifier.width(100.dp)
                    )
                    Text(
                        text = "Попался, Гринч!",
                        fontSize = 36.sp,
                        fontFamily = Fonts.Pacifico,
                        color = Colors.AccentColor
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        textAlign = TextAlign.Center,
                        text = message,
                        fontSize = 18.sp,
                        fontFamily = Fonts.RobotoRegular,
                        color = Colors.AccentColor
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    AppButton(
                        onClick = onHide,
                        title = "Я исправлюсь",
                        disabled = false,
                        width = 180.dp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

            }
        }
    }
}