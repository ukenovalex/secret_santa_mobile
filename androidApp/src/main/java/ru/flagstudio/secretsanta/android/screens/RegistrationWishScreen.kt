package ru.flagstudio.secretsanta.android.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adeo.kviewmodel.compose.observeAsState
import ru.flagstudio.secretsanta.android.ui.*
import ru.flagstudio.secretsanta.android.ui.theme.Colors
import ru.flagstudio.secretsanta.android.ui.theme.Fonts
import user.UserEvent
import user.UserViewModel

@Composable
fun RegistrationWishScreen(viewModel: UserViewModel, navigateToCongrat: () -> Unit) {

    val state = viewModel.viewStates().observeAsState()

    LaunchedEffect(null) {
        viewModel.obtainEvent(UserEvent.GetUserInfo)
    }

    AuthContainer {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AppTitle(text = "Tell about your interests")
            Spacer(modifier = Modifier.height(62.dp))
            AppTextField(
                value = state.value.currentWishValue,
                onValueChange = { viewModel.obtainEvent(UserEvent.InputWish(it)) },
                label = "Interest",
                placeholder = ""
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                SecondaryButton(onClick = { viewModel.obtainEvent(UserEvent.AddWish) }, title = "Add")
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
                if (!state.value.wishes.isNullOrEmpty()) {
                    Text(
                        color = Colors.TextColor,
                        text = "Tap to remove",
                        fontFamily = Fonts.RobotoLight,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            FlowRow(horizontalGap = 15.dp, verticalGap = 20.dp) {
                state.value.wishes?.forEach {
                    WishItem(it.message) {
                        viewModel.obtainEvent(UserEvent.RemoveWish(it.id))
                    }
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            AppButton(onClick = navigateToCongrat, title = "Done", disabled = false)
        }
    }
}