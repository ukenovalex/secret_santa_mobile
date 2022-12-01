package ru.flagstudio.secretsanta.android.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import auth.AuthViewModel
import register.RegisterViewModel
import ru.flagstudio.secretsanta.android.R
import ru.flagstudio.secretsanta.android.screens.*
import ru.flagstudio.secretsanta.android.ui.theme.Colors
import user.UserViewModel



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
    )
    Scaffold(
        backgroundColor = Colors.Background,
        bottomBar = {
            val backStackEntry = navController.currentBackStackEntryAsState()
            val isShowBottomBar = bottomNavItems.find {
                it.route == backStackEntry.value?.destination?.route
            } != null
            if (isShowBottomBar) {
                BottomNavBar(
                    items = bottomNavItems,
                    navController = navController,
                    onItemClick = { navController.navigate(it.route) },
                    modifier = Modifier.height(80.dp)
                )
            }
        },
    ) {
        Host(navController)
    }
}

