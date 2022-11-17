package ru.flagstudio.secretsanta.android.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import auth.AuthViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val authViewModel = AuthViewModel()
    NavHost(navController = navController, startDestination = "auth") {
        composable("auth") { AuthScreen(viewModel = authViewModel, onNavigateToTest = {
            navController.navigate("test")
            authViewModel.clear()
        }) }
        composable("test") { TestScreen() }
    }
}