package ru.flagstudio.secretsanta.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.flagstudio.secretsanta.android.components.WishesComponent
import ru.flagstudio.secretsanta.android.ui.AppButton
import ru.flagstudio.secretsanta.android.ui.MainContainer
import user.UserViewModel

@Composable
fun RegistrationWishScreen(viewModel: UserViewModel, navigateToCongrat: () -> Unit) {
    MainContainer(paddingHorizontal = 12.dp) {
        WishesComponent(viewModel = viewModel)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            AppButton(onClick = navigateToCongrat, title = "Done", disabled = false)
        }
    }
}