package ru.flagstudio.secretsanta.android.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.flagstudio.secretsanta.android.ui.*
import ru.flagstudio.secretsanta.android.ui.theme.Colors
import ru.flagstudio.secretsanta.android.ui.theme.Fonts

@Composable
fun RegistrationWishScreen() {
    AuthContainer {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AppTitle(text = "Tell about your interests")
            Spacer(modifier = Modifier.height(62.dp))
            AppTextField(value = "", onValueChange = {}, label = "Interest", placeholder = "")
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                SecondaryButton(onClick = { /*TODO*/ }, title = "Add")
            }
        }
        Column {
            Column(modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    drawLine(
                        color = Colors.TextColor,
                        start = Offset(-16.dp.toPx(), size.height),
                        end = Offset(size.width + 16.dp.toPx(), size.height),
                        strokeWidth = 1.dp.toPx()
                    )
                }
            ) {
                Text(
                    color = Colors.TextColor,
                    text = "Tap to remove",
                    fontFamily = Fonts.RobotoLight,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            FlowRow(horizontalGap = 15.dp, verticalGap = 20.dp) {
                WishItem("Test")
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            AppButton(onClick = { /*TODO*/ }, title = "Done", disabled = false)
        }
    }
}