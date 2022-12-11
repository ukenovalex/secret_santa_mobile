package ru.flagstudio.secretsanta.android.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.flagstudio.secretsanta.android.R
import ru.flagstudio.secretsanta.android.screens.*
import ru.flagstudio.secretsanta.android.ui.theme.Colors


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val bottomNavItems = listOf(
        BottomNavItem(
            name = "Profile",
            route = Routes.Profile,
            icon = painterResource(id = R.drawable.profile_nav_icon),
        ),
        BottomNavItem(
            name = "Wish",
            route = Routes.ProfileWish,
            icon = painterResource(id = R.drawable.wish_nav_icon),
        ),
        BottomNavItem(
            name = "UserList",
            route = Routes.ProfileUserList,
            icon = painterResource(id = R.drawable.user_list_nav_item),
        ),
    )
    Scaffold(
        backgroundColor = Colors.Transparent,
        bottomBar = {
            val backStackEntry = navController.currentBackStackEntryAsState()
            val isShowBottomBar = bottomNavItems.find {
                it.route == backStackEntry.value?.destination?.route
            } != null
            if (isShowBottomBar) {
                BottomNavBar(
                    items = bottomNavItems,
                    navController = navController,
                    onItemClick = { item ->
                        navController.navigate(item.route) {
                            navController.currentDestination?.route?.let {
                                popUpTo(it) {
                                    inclusive = true
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxHeight(0.1f)
                )
            }
        },
    ) {
        Host(navController)
    }
}

