package ru.flagstudio.secretsanta.android.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import auth.AuthViewModel
import register.RegisterViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val authViewModel = AuthViewModel()
    val registerViewModel = RegisterViewModel()

    NavHost(navController = navController, startDestination = "auth") {
        composable("auth") { AuthScreen(viewModel = authViewModel, onNavigateToRegister = {
            navController.navigate("register")
            authViewModel.clear()
        }) }
        composable("register") { RegisterScreen(viewModel = registerViewModel) }
    }
}