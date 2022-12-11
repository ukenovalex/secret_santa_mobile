package ru.flagstudio.secretsanta.android.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.flagstudio.secretsanta.android.components.WishesComponent
import ru.flagstudio.secretsanta.android.ui.MainContainer
import user.UserViewModel

@Composable
fun ProfileWishScreen(viewModel: UserViewModel) {
    MainContainer(paddingHorizontal = 12.dp) {
        Column(modifier = Modifier.fillMaxHeight(0.9f)) {
            WishesComponent(viewModel)
        }
    }
}