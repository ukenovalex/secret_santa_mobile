package ru.flagstudio.secretsanta.android.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adeo.kviewmodel.compose.observeAsState
import my.nanihadesuka.compose.LazyColumnScrollbar
import ru.flagstudio.secretsanta.android.R
import ru.flagstudio.secretsanta.android.ui.theme.Colors
import ru.flagstudio.secretsanta.android.ui.theme.Fonts
import user.UserEvent
import user.UserViewModel

@Composable
fun ProfileUserList(viewModel: UserViewModel) {
    val lazyListState = rememberLazyListState()
    val state = viewModel.viewStates().observeAsState()
    LaunchedEffect(Unit) {
        viewModel.obtainEvent(UserEvent.GetAllUsers)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Colors.Background)
    ) {
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight(0.2f)) {
            Text(
                textAlign = TextAlign.Center,
                fontFamily = Fonts.Pacifico,
                fontSize = 36.sp,
                color = Colors.TextColor,
                text = "Малыши и санты",
                modifier = Modifier.fillMaxWidth()
            )
        }
        Box(modifier = Modifier.fillMaxHeight(0.85f)) {
            LazyColumnScrollbar(lazyListState,
                thumbColor = Colors.TextColor,
                thumbSelectedColor = Colors.TextColor,
                content = {
                    LazyColumn(state = lazyListState) {
                        items(state.value.users) {
                            Row(horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .drawBehind {
                                        drawLine(
                                            color = Colors.TextColor,
                                            start = Offset(0f, size.height),
                                            end = Offset(size.width, size.height),
                                            strokeWidth = 1.dp.toPx()
                                        )
                                    }
                                    .padding(horizontal = 16.dp)) {
                                Text(
                                    color = Colors.TextColor,
                                    fontFamily = Fonts.RobotoRegular,
                                    fontSize = 18.sp,
                                    text = it.name
                                )
                                if (it.isSanta) {
                                    Image(
                                        painter = painterResource(id = R.drawable.santa_icon),
                                        contentDescription = "Santa Icon",
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }
                        }
                    }
                })
        }
    }
}
