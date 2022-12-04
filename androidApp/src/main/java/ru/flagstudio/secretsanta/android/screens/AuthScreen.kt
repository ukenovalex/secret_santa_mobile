package ru.flagstudio.secretsanta.android.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import auth.AuthEvent
import auth.AuthViewModel
import auth.model.LoginStatus
import com.adeo.kviewmodel.compose.observeAsState
import ru.flagstudio.secretsanta.android.ui.AppButton
import ru.flagstudio.secretsanta.android.ui.AppTextField
import ru.flagstudio.secretsanta.android.ui.AppTitle
import ru.flagstudio.secretsanta.android.ui.MainContainer

@Composable
fun AuthScreen(
    viewModel: AuthViewModel,
    onNavigateToRegister: () -> Unit,
    onNavigateToCongrat: () -> Unit
) {
    val state = viewModel.viewStates().observeAsState()

    LaunchedEffect(state.value.loginStatus) {
        if (state.value.loginStatus == LoginStatus.SUCCESS) {
            onNavigateToCongrat()
        }
    }

    MainContainer {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxHeight(0.5f)
        ) {
            AppTitle("Добро пожаловать, санта-малютка!")
            Spacer(modifier = Modifier.height(24.dp))
            AppTextField(
                value = state.value.email,
                onValueChange = {
                    viewModel.obtainEvent(AuthEvent.InputEmail(it))
                },
                label = "E-mail:",
                placeholder = "santa@flagstudio.ru",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(modifier = Modifier.height(20.dp))
            AppTextField(
                value = state.value.password,
                onValueChange = {
                    viewModel.obtainEvent(AuthEvent.InputPassword(it))
                },
                label = "Пароль:",
                placeholder = "Твой секретик...",
                hidden = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)

            )
        }
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight(0.5f)) {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                AppButton(
                    onClick = { viewModel.obtainEvent(AuthEvent.PressLogin) },
                    title = "Войти",
                    disabled = !state.value.validForm || state.value.loginStatus == LoginStatus.LOADING,
                )
                AppButton(
                    onClick = { onNavigateToRegister() },
                    title = "Создать",
                    disabled = state.value.loginStatus == LoginStatus.LOADING
                )
            }
        }
    }
}