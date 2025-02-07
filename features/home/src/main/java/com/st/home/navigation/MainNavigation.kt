package com.st.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.st.home.presentation.favorites.FavoritesScreen
import com.st.home.presentation.player.PlayerScreen
import com.st.home.presentation.playlist.PlaylistScreen
import com.st.home.presentation.search.SearchScreen
import com.st.home.presentation.terms.TermsScreen


object NavRoutes {
    const val SEARCH = "search"
    const val PLAYER = "player/{videoId}"
    const val PLAYLIST = "playlist"
    const val FAVORITES = "favorites"
    const val TERMS = "terms"
}

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavRoutes.SEARCH) {
        composable(NavRoutes.SEARCH) {
            SearchScreen(navController)
        }
        composable(NavRoutes.PLAYER) { backStackEntry ->
            val videoId = backStackEntry.arguments?.getString("videoId").orEmpty()
            PlayerScreen(videoId)
        }
        composable(NavRoutes.PLAYLIST) {
            PlaylistScreen(navController)
        }
        composable(NavRoutes.FAVORITES) { FavoritesScreen(navController) }
        composable(NavRoutes.TERMS) { TermsScreen() }
    }
}

