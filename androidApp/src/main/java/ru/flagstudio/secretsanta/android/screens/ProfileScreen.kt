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
import ru.flagstudio.secretsanta.android.ui.SecondaryButton
import ru.flagstudio.secretsanta.android.ui.theme.Colors
import ru.flagstudio.secretsanta.android.ui.theme.Fonts
import santa.SantaEvent
import santa.SantaViewModel
import santa.models.SantaDataStatus

@Composable
fun ProfileScreen(viewModel: SantaViewModel) {
    val state = viewModel.viewStates().observeAsState()


    LaunchedEffect(state.value.fetchDataStatus) {
        if (state.value.fetchDataStatus == SantaDataStatus.EMPTY) {
            viewModel.obtainEvent(SantaEvent.FetchSantaInfo)
        }
    }

    if (state.value.fetchDataStatus == SantaDataStatus.SUCCESS) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(130.dp))
                Text(
                    fontFamily = Fonts.RobotoBold,
                    text = state.value.userName,
                    color = Colors.TextColor,
                    fontSize = 32.sp
                )
                if (!state.value.isSanta) {
                    Spacer(modifier = Modifier.height(130.dp))
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
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .offset(y = 20.dp)
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
                    }
                    Row(
                        horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.secret_santa),
                            contentDescription = "Secret Santa",
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier.height(282.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(70.dp))
            }
        }
    }


}
