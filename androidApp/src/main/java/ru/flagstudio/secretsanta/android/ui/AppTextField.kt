package ru.flagstudio.secretsanta.android.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.flagstudio.secretsanta.android.ui.theme.Colors
import ru.flagstudio.secretsanta.android.ui.theme.Fonts


@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    hidden: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
) {

    var isFocused by remember {
        mutableStateOf(false)
    }

    Column {
        Text(
            text = label,
            color = Colors.TextColor,
            fontFamily = Fonts.RobotoMedium,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        BasicTextField(
            keyboardOptions = keyboardOptions,
            singleLine = true,
            cursorBrush = SolidColor(Colors.TextColor),
            visualTransformation = if (hidden) {
                PasswordVisualTransformation()
            } else VisualTransformation.None,
            modifier = Modifier
                .drawBehind {
                    drawLine(
                        color = Colors.TextColor,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 1.dp.toPx()
                    )
                }
                .onFocusEvent {
                    isFocused = it.isFocused
                },
            textStyle = TextStyle(
                color = Colors.TextColor,
                fontFamily = Fonts.RobotoLight,
                fontSize = 16.sp,
            ),
            value = value,
            onValueChange = onValueChange,
            decorationBox = { InnerTextField ->
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp, 11.dp, 11.dp, 8.dp)
                    ) {
                        if (value.isEmpty() && !isFocused) {
                            Text(
                                text = placeholder,
                                color = Colors.TextColor,
                                fontFamily = Fonts.RobotoLight,
                                fontSize = 16.sp,
                            )
                        } else {
                            InnerTextField()
                        }
                    }
                }
            },
        )
    }
}

