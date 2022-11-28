package ru.flagstudio.secretsanta.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import auth.AuthEvent
import auth.AuthViewModel
import com.adeo.kviewmodel.compose.observeAsState
import ru.flagstudio.secretsanta.android.ui.AppButton
import ru.flagstudio.secretsanta.android.ui.AppTextField
import ru.flagstudio.secretsanta.android.ui.AppTitle
import ru.flagstudio.secretsanta.android.ui.theme.Color

@Composable
fun AuthScreen(viewModel: AuthViewModel, onNavigateToTest: () -> Unit) {
    val state = viewModel.viewStates().observeAsState()
    val focusManager = LocalFocusManager.current
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
                value = state.value.email,
                onValueChange = {
                    viewModel.obtainEvent(AuthEvent.InputEmail(it))
                },
                label = "E-mail:",
                placeholder = "Enter e-mail...",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(modifier = Modifier.height(20.dp))
            AppTextField(
                value = state.value.password,
                onValueChange = {
                    viewModel.obtainEvent(AuthEvent.InputPassword(it))
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
                    onClick = { viewModel.obtainEvent(AuthEvent.PressLogin) },
                    title = "Login",
                    disabled = !state.value.validForm
                )
                AppButton(
                    onClick = { viewModel.obtainEvent(AuthEvent.PressLogin) },
                    title = "Register",
                    disabled = false
                )
            }
        }
    }
}