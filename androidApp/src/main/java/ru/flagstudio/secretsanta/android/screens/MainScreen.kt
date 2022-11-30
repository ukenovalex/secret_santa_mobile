package ru.flagstudio.secretsanta.android.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import auth.AuthViewModel
import register.RegisterViewModel
import ru.flagstudio.secretsanta.android.navigation.Routes
import user.UserViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val authViewModel = AuthViewModel()
    val registerViewModel = RegisterViewModel()
    val userViewModel = UserViewModel()

    NavHost(navController = navController, startDestination = Routes.RegisterWish) {
        composable(Routes.Auth) { AuthScreen(viewModel = authViewModel, onNavigateToRegister = {
            navController.navigate(Routes.Register)
            authViewModel.clear()
        }) }
        composable(Routes.Register) { RegisterScreen(viewModel = registerViewModel, navigateToRegistrationWish = {
            navController.navigate(Routes.RegisterWish)
            registerViewModel.clear()
        }) }
        composable(Routes.RegisterWish) { RegistrationWishScreen(viewModel = userViewModel) }
    }
}