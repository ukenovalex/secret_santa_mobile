package ru.flagstudio.secretsanta.android.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.adeo.kviewmodel.compose.observeAsState
import ru.flagstudio.secretsanta.android.ui.AppButton
import ru.flagstudio.secretsanta.android.ui.AppTitle
import santa.SantaEvent
import santa.SantaViewModel

@Composable
fun ProfileScreen(viewModel: SantaViewModel) {
    val state = viewModel.viewStates().observeAsState()


    LaunchedEffect(null) {
        viewModel.obtainEvent(SantaEvent.FetchSantaInfo)
    }

    Column {
        Text(text = state.value.userName)
        Text(text = state.value.giftedName ?: "")
        if (!state.value.isSanta) {
            AppButton(
                onClick = { viewModel.obtainEvent(SantaEvent.BecomeSanta) },
                title = "BecomeSanta",
                disabled = false
            )
        }
    }
}