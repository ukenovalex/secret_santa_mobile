package ru.flagstudio.secretsanta.android.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import user.models.AddWishStatus
import user.models.FetchUserStatus
import user.models.RemoveWishStatus

@Composable
fun WishesComponent(viewModel: UserViewModel) {
    val state = viewModel.viewStates().observeAsState()

    LaunchedEffect(state.value.fetchUserStatus) {
        if (state.value.fetchUserStatus == FetchUserStatus.EMPTY) {
            viewModel.obtainEvent(UserEvent.GetUserInfo)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight(0.5f)
    ) {
        AppDialogError(
            isShow = state.value.fetchUserStatus == FetchUserStatus.ERROR,
            message = "Санта тебя не узнал. Попробуй еще раз!"
        ) {
            viewModel.obtainEvent(UserEvent.GetUserInfo)
        }
        AppDialogError(
            isShow = state.value.addWishStatus == AddWishStatus.ERROR,
            message = "Твое пожелание не дошло до санты. Попробуй еще раз!"
        ) {
            viewModel.obtainEvent(UserEvent.ChangeAddWishStatus(AddWishStatus.EMPTY))
        }
        AppDialogError(
            isShow = state.value.removeWishStatus == RemoveWishStatus.ERROR,
            message = "Не получилось избавиться от твоего желания. Хоти аккуратней!"
        ) {
            viewModel.obtainEvent(UserEvent.ChangeRemoveWishStatus(RemoveWishStatus.EMPTY))
        }
        Box(modifier = Modifier.fillMaxHeight(0.3f)) {
            AppTitle(text = "Расскажи о своем подарке мечты")
        }
        AppTextField(
            value = state.value.currentWishValue,
            onValueChange = { viewModel.obtainEvent(UserEvent.InputWish(it)) },
            label = "Интересует",
            placeholder = ""
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            SecondaryButton(
                width = 150.dp,
                onClick = { viewModel.obtainEvent(UserEvent.AddWish) },
                title = "Добавить"
            )
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
            }) {
            if (!state.value.wishes.isNullOrEmpty()) {
                Text(
                    color = Colors.TextColor,
                    text = "Нажми для удаления",
                    fontFamily = Fonts.RobotoLight,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier
            .height(150.dp)
            .drawBehind {
                drawLine(
                    color = Colors.TextColor,
                    start = Offset(-16.dp.toPx(), size.height),
                    end = Offset(size.width + 16.dp.toPx(), size.height),
                    strokeWidth = 1.dp.toPx()
                )
            }
            .verticalScroll(rememberScrollState())
            .padding(bottom = 20.dp)) {
            FlowRow(horizontalGap = 15.dp, verticalGap = 20.dp) {
                state.value.wishes?.forEach {
                    WishItem(it.message) {
                        viewModel.obtainEvent(UserEvent.RemoveWish(it.id))
                    }
                }
            }
        }
    }
}