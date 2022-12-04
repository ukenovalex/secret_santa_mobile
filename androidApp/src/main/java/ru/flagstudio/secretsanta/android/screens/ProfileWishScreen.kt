package ru.flagstudio.secretsanta.android.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
fun ProfileWishScreen(viewModel: UserViewModel) {
    MainContainer {
        WishesComponent(viewModel)
    }
}