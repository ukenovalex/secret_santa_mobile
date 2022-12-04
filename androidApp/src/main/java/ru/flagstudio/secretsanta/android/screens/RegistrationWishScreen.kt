package ru.flagstudio.secretsanta.android.screens

import androidx.compose.foundation.layout.*
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
import ru.flagstudio.secretsanta.android.components.WishesComponent
import ru.flagstudio.secretsanta.android.ui.*
import ru.flagstudio.secretsanta.android.ui.theme.Colors
import ru.flagstudio.secretsanta.android.ui.theme.Fonts
import user.UserEvent
import user.UserViewModel

@Composable
fun RegistrationWishScreen(viewModel: UserViewModel, navigateToCongrat: () -> Unit) {
    MainContainer {
        WishesComponent(viewModel = viewModel)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            AppButton(onClick = navigateToCongrat, title = "Done", disabled = false)
        }
    }
}