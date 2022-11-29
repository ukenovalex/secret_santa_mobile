package ru.flagstudio.secretsanta.android.screens

import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import auth.AuthEvent
import com.adeo.kviewmodel.compose.observeAsState
import register.RegisterEvent
import register.RegisterViewModel
import register.model.RegisterStatus
import ru.flagstudio.secretsanta.android.ui.AppButton
import ru.flagstudio.secretsanta.android.ui.AppTextField
import ru.flagstudio.secretsanta.android.ui.AppTitle


@Composable
fun RegisterScreen(viewModel: RegisterViewModel) {
    val state = viewModel.viewStates().observeAsState()
    val focusManager = LocalFocusManager.current

    if (state.value.status == RegisterStatus.SUCCESS) {
        println("SUCEEEEEES")
    }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp)
            .fillMaxSize(1f)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus(true)
                })
            }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxHeight(0.5f)
        ) {
            AppTitle("Introduce yourself, naughty kid!")
            Spacer(modifier = Modifier.height(24.dp))
            AppTextField(
                value = state.value.name,
                onValueChange = {
                    viewModel.obtainEvent(RegisterEvent.InputName(it))
                },
                label = "Name:",
                placeholder = "Enter name..."
            )
            Spacer(modifier = Modifier.height(20.dp))
            AppTextField(
                value = state.value.email,
                onValueChange = {
                    viewModel.obtainEvent(RegisterEvent.InputEmail(it))
                },
                label = "E-mail:",
                placeholder = "Enter e-mail...",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(modifier = Modifier.height(20.dp))
            AppTextField(
                value = state.value.password,
                onValueChange = {
                    viewModel.obtainEvent(RegisterEvent.InputPassword(it))
                },
                label = "Password:",
                placeholder = "Enter password...",
                hidden = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)

            )
        }
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight(0.5f)) {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                AppButton(
                    onClick = { viewModel.obtainEvent(RegisterEvent.PressRegister) },
                    title = "Done",
                    disabled = !state.value.validForm
                )
            }
        }
    }
}