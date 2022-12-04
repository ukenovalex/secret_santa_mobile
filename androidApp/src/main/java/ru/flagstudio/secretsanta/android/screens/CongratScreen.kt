package ru.flagstudio.secretsanta.android.screens

import android.os.Handler
import android.os.Looper
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.flagstudio.secretsanta.android.R
import ru.flagstudio.secretsanta.android.ui.MainContainer
import ru.flagstudio.secretsanta.android.ui.theme.Colors
import ru.flagstudio.secretsanta.android.ui.theme.Fonts

@Composable
fun CongratScreen(navigateToProfile: () -> Unit) {

    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(null) {
        visible = true
    }

    DisposableEffect(Unit) {
        val handler = Handler(Looper.getMainLooper())
        val runnable = {
            navigateToProfile()
        }
        handler.postDelayed(runnable, 2500)
        onDispose {
            handler.removeCallbacks(runnable)
        }
    }

    MainContainer {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(2000)),
                exit = fadeOut(animationSpec = tween(2000))
            ) {
                Text(
                    text = "Congratulations!",
                    fontFamily = Fonts.Pacifico,
                    fontSize = 42.sp,
                    color = Colors.TextColor
                )
            }
            Spacer(modifier = Modifier.height(84.dp))
            Image(
                painter = painterResource(id = R.drawable.christmas_tree),
                contentDescription = "christmas tree",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Preview
@Composable
fun CongratScreen_Preview() {
    CongratScreen {}
}