package ru.flagstudio.secretsanta.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import auth.AuthEvent
import auth.AuthViewModel
import auth.model.LoginStatus
import com.adeo.kviewmodel.compose.observeAsState
import ru.flagstudio.secretsanta.android.R
import ru.flagstudio.secretsanta.android.ui.theme.Colors
import ru.flagstudio.secretsanta.android.ui.theme.Fonts


@Composable
fun FirstScreen(
    viewModel: AuthViewModel,
    navigateToProfile: () -> Unit,
    navigateToLogin: () -> Unit,
) {
    val state = viewModel.viewStates().observeAsState()

    LaunchedEffect(key1 = state.value.loginStatus) {
        when (state.value.loginStatus) {
            LoginStatus.SUCCESS -> {
                navigateToProfile()
            }
            LoginStatus.NOT_VERIFIED -> {
                navigateToLogin()
            }
            else -> {}
        }
    }

    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Colors.SecondaryBackground)
            .paint(
                painter = painterResource(id = R.drawable.main_screen_image),
                contentScale = ContentScale.Fit
            )
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                viewModel.obtainEvent(AuthEvent.CheckLoginStatus)
            }
    ) {
        Text(
            text = "Ho Ho Ho!\n You little bitch!",
            textAlign = TextAlign.Center,
            color = Colors.AccentColor,
            fontFamily = Fonts.Pacifico,
            fontSize = 48.sp,
            modifier = Modifier.rotate(-3.73f)
        )
        Spacer(modifier = Modifier.height(48.dp))
        Icon(
            painter = painterResource(id = R.drawable.tap_icon),
            contentDescription = "tap icon",
            tint = Colors.AccentColor
        )
        Text(
            text = "Tap here",
            textAlign = TextAlign.Center,
            color = Colors.AccentColor,
            fontFamily = Fonts.Pacifico,
            fontSize = 24.sp,
        )
        Spacer(modifier = Modifier.height(85.dp))
    }
}

