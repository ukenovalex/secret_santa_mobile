package ru.flagstudio.secretsanta.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import auth.AuthViewModel
import ru.flagstudio.secretsanta.android.screens.*
import santa.SantaViewModel
import user.UserViewModel

@Composable
fun Host(navController: NavHostController) {
    val authViewModel = AuthViewModel()
    val userViewModel = UserViewModel()
    val santaViewModel = SantaViewModel()

    NavHost(navController = navController, startDestination = Routes.FirstScreen) {
        composable(Routes.Auth) {
            AuthScreen(
                viewModel = authViewModel,
                onNavigateToWishScreen = {
                    navController.navigate(Routes.RegisterWish)
                },
                onNavigateToCongrat = {
                    navController.navigate(Routes.Congrat) {
                        popUpTo(Routes.Auth) {
                            inclusive = true
                        }
                    }
                }
            )
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
        composable(Routes.Profile) { ProfileScreen(viewModel = santaViewModel) }
        composable(Routes.ProfileWish) { ProfileWishScreen(viewModel = userViewModel) }
        composable(Routes.ProfileUserList) { ProfileUserList(viewModel = userViewModel) }

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