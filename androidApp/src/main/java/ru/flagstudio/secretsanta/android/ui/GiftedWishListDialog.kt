package ru.flagstudio.secretsanta.android.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import my.nanihadesuka.compose.ColumnScrollbar
import ru.flagstudio.secretsanta.android.ui.theme.Colors
import ru.flagstudio.secretsanta.android.ui.theme.Fonts

@Composable
fun GiftedWishListDialog(
    isShow: Boolean,
    name: String,
    wishList: List<String>,
    onHide: () -> Unit
) {
    if (isShow) {
        val state = rememberScrollState()
        Dialog(onDismissRequest = onHide) {
            Card(
                backgroundColor = Colors.SecondaryBackground,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = name,
                        color = Colors.AccentColor,
                        fontSize = 24.sp,
                        fontFamily = Fonts.Pacifico
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Я прошу у Санты антидепрессанты. А ещё:",
                        color = Colors.AccentColor,
                        fontSize = 16.sp,
                        fontFamily = Fonts.Pacifico
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(modifier = Modifier.height(120.dp)) {
                        ColumnScrollbar(
                            state = state,
                            thumbColor = Colors.AccentColor,
                            thumbSelectedColor = Colors.AccentColor,
                        ) {
                            Box(
                                Modifier
                                    .verticalScroll(state = state)
                                    .fillMaxWidth()
                                    .padding(end = 8.dp),
                            ) {
                                FlowRow(horizontalGap = 12.dp, verticalGap = 16.dp) {
                                    wishList.forEach {
                                        WishItemDark(it)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}