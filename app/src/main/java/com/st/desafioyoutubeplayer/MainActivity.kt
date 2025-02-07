package com.st.desafioyoutubeplayer

import AuthScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import com.st.auth.presentation.GoogleAuthUiClient
import com.st.desafioyoutubeplayer.navigation.AppNavigationGraph
import com.st.desafioyoutubeplayer.navigation.Routes
import com.st.desafioyoutubeplayer.ui.theme.DesafioYoutubePlayerTheme
import com.st.home.presentation.MainScreen

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DesafioYoutubePlayerTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Routes.LoginScreen) {
                    composable(Routes.LoginScreen) {
                        AuthScreen(
                            navigateToHome = {
                                navController.navigate("main") {
                                    popUpTo("auth") { inclusive = true }
                                }
                            },
                        )
                    }
                    composable(Routes.MainScreen) {
                        MainScreen()
                    }
                    composable(Routes.MainScreen) {
                        MainScreen()
                    }
                }
            }
        }
    }
}

