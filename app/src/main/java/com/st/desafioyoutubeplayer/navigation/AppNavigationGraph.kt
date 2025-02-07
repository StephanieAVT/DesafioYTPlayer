package com.st.desafioyoutubeplayer.navigation

import AuthScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.st.home.presentation.MainScreen

@Composable
fun AppNavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.LoginScreen) {
        composable(Routes.LoginScreen) {
            AuthScreen(
                navigateToHome = {
                navController.navigate("main") {
                    popUpTo("auth") { inclusive = true }
                }
            }
            )
        }
        composable(Routes.MainScreen) {
            MainScreen()
        }
    }
}