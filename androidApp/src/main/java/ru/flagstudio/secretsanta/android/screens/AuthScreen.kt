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
import ru.flagstudio.secretsanta.android.ui.*

@Composable
fun AuthScreen(
    viewModel: AuthViewModel,
    onNavigateToWishScreen: () -> Unit,
    onNavigateToCongrat: () -> Unit
) {
    val state = viewModel.viewStates().observeAsState()

    LaunchedEffect(state.value.loginStatus) {
        if (state.value.loginStatus == LoginStatus.SUCCESS) {
            if (state.value.isUserExist) {
                onNavigateToCongrat()
            } else {
                onNavigateToWishScreen()
            }
        }
    }

    MainContainer(paddingHorizontal = 12.dp) {
        AppDialogError(
            isShow = state.value.loginStatus == LoginStatus.ERROR,
            message = "Всё сломалось :( Убедись что твой корпоративный почтовый ящик и пароль верны."
        ) {
            viewModel.obtainEvent(AuthEvent.ChangeLoginStatus(LoginStatus.NOT_VERIFIED))
        }
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
            }
        }
    }
}