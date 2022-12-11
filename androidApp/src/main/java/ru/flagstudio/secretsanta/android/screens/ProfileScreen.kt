package ru.flagstudio.secretsanta.android.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adeo.kviewmodel.compose.observeAsState
import ru.flagstudio.secretsanta.android.R
import ru.flagstudio.secretsanta.android.ui.AppDialogError
import ru.flagstudio.secretsanta.android.ui.GiftedWishListDialog
import ru.flagstudio.secretsanta.android.ui.SecondaryButton
import ru.flagstudio.secretsanta.android.ui.theme.Colors
import ru.flagstudio.secretsanta.android.ui.theme.Fonts
import santa.SantaEvent
import santa.SantaViewModel
import santa.models.SantaStatus

@Composable
fun ProfileScreen(viewModel: SantaViewModel) {
    val state = viewModel.viewStates().observeAsState()


    LaunchedEffect(Unit) {
        viewModel.obtainEvent(SantaEvent.FetchSantaInfo)
    }

    if (state.value.fetchStatus == SantaStatus.SUCCESS) {
        AppDialogError(
            isShow = state.value.fetchStatus == SantaStatus.BECOME_ERROR,
            message = "Все послушные малыши закончились. Можешь сделать подарок сам себе :)"
        ) {
            viewModel.obtainEvent(SantaEvent.ChangeFetchStatus(SantaStatus.SUCCESS))
        }
        AppDialogError(
            isShow = state.value.fetchStatus == SantaStatus.ERROR,
            message = "Санта тебя не узнал. Попробуй еще раз!"
        ) {
            viewModel.obtainEvent(SantaEvent.FetchSantaInfo)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 12.dp, end = 12.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f)
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    fontFamily = Fonts.RobotoBold,
                    text = state.value.userName,
                    color = Colors.TextColor,
                    fontSize = 32.sp
                )
            }
            if (!state.value.isSanta) {
                Column(
                    verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()
                ) {
                    SecondaryButton(
                        onClick = { viewModel.obtainEvent(SantaEvent.BecomeSanta) },
                        title = "Вжух, и я санта",
                        width = 250.dp
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
            ) {
                if (state.value.isSanta) {
                    GiftedWishListDialog(
                        name = state.value.giftedName ?: "",
                        isShow = state.value.isShowGiftedWishDialog,
                        wishList = state.value.giftedWishList
                    ) {
                        viewModel.obtainEvent(SantaEvent.HideGiftedWishDialog)
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            textAlign = TextAlign.Center,
                            fontFamily = Fonts.Pacifico,
                            text = "Твой послушный малыш:",
                            color = Colors.TextColor,
                            fontSize = 28.sp,
                        )
                        Text(
                            textAlign = TextAlign.Center,
                            text = state.value.giftedName ?: "",
                            fontFamily = Fonts.Pacifico,
                            fontSize = 48.sp,
                            color = Colors.TextColor,
                            modifier = Modifier.rotate(-3.92f)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        if (state.value.giftedWishList.isNotEmpty()) {
                            SecondaryButton(
                                onClick = { viewModel.obtainEvent(SantaEvent.ShowGiftedWishDialog) },
                                title = "Интересы",
                                width = 250.dp
                            )
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.secret_santa),
                            contentDescription = "Secret Santa",
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier
                                .height(282.dp)
                                .offset(x = 12.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(70.dp))
            }
        }
    }
}
