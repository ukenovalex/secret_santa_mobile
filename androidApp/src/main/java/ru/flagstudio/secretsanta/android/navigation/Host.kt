package ru.flagstudio.secretsanta.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import auth.AuthViewModel
import register.RegisterViewModel
import ru.flagstudio.secretsanta.android.screens.*
import user.UserViewModel

@Composable
fun Host(navController: NavHostController) {
    val authViewModel = AuthViewModel()
    val registerViewModel = RegisterViewModel()
    val userViewModel = UserViewModel()

    NavHost(navController = navController, startDestination = Routes.FirstScreen) {
        composable(Routes.Auth) {
            AuthScreen(viewModel = authViewModel, onNavigateToRegister = {
                navController.navigate(Routes.Register)
            }, onNavigateToCongrat = {
                navController.navigate(Routes.Congrat) {
                    popUpTo(Routes.Auth) {
                        inclusive = true
                    }
                }
                authViewModel.clear()
            })
        }
        composable(Routes.Register) {
            RegisterScreen(viewModel = registerViewModel, navigateToRegistrationWish = {
                navController.navigate(Routes.RegisterWish)
                registerViewModel.clear()
            })
        }
        composable(Routes.RegisterWish) {
            RegistrationWishScreen(viewModel = userViewModel, navigateToCongrat = {
                navController.navigate(Routes.Congrat) {
                    popUpTo(Routes.RegisterWish) {
                        inclusive = true
                    }
                }
            })
        }
        composable(Routes.Congrat) {
            CongratScreen(navigateToProfile = {
                navController.navigate(Routes.Profile) {
                    popUpTo(Routes.Congrat) {
                        inclusive = true
                    }
                }
            })
        }
        composable(Routes.Profile) { ProfileScreen() }
        composable(Routes.ProfileWish) { ProfileWishScreen() }

        composable(Routes.FirstScreen) {
            FirstScreen(
                viewModel = authViewModel,
                navigateToProfile = {
                    navController.navigate(Routes.Profile) {
                        popUpTo(Routes.FirstScreen) {
                            inclusive = true
                        }
                    }
                },
                navigateToLogin = {
                    navController.navigate(Routes.Auth) {
                        popUpTo(Routes.FirstScreen) {
                            inclusive = true
                        }
                    }
                }
            )
        }

    }
}