package ru.flagstudio.secretsanta.android.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.adeo.kviewmodel.compose.observeAsState
import register.RegisterEvent
import register.RegisterViewModel
import register.model.RegisterStatus
import ru.flagstudio.secretsanta.android.ui.AppButton
import ru.flagstudio.secretsanta.android.ui.AppTextField
import ru.flagstudio.secretsanta.android.ui.AppTitle
import ru.flagstudio.secretsanta.android.ui.MainContainer


@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    navigateToRegistrationWish: () -> Unit,
) {
    val state = viewModel.viewStates().observeAsState()

    LaunchedEffect(state.value.status) {
        if (state.value.status == RegisterStatus.SUCCESS) {
            navigateToRegistrationWish()
        }
    }

    MainContainer(paddingHorizontal = 12.dp) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxHeight(0.5f)
        ) {
            AppTitle("Хочешь подарок? Создай аккаунт!")
            Spacer(modifier = Modifier.height(24.dp))
            AppTextField(
                value = state.value.name,
                onValueChange = {
                    viewModel.obtainEvent(RegisterEvent.InputName(it))
                },
                label = "Имя:",
                placeholder = "Рождественский Валерий Ильич"
            )
            Spacer(modifier = Modifier.height(20.dp))
            AppTextField(
                value = state.value.email,
                onValueChange = {
                    viewModel.obtainEvent(RegisterEvent.InputEmail(it))
                },
                label = "E-mail:",
                placeholder = "santa@flagstudio.ru",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(modifier = Modifier.height(20.dp))
            AppTextField(
                value = state.value.password,
                onValueChange = {
                    viewModel.obtainEvent(RegisterEvent.InputPassword(it))
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
                    width = 180.dp,
                    onClick = { viewModel.obtainEvent(RegisterEvent.PressRegister) },
                    title = "Хочу подарок",
                    disabled = !state.value.validForm,
                )
            }
        }
    }
}