package com.st.home.domain.favorites.repository

import com.st.home.domain.search.model.YouTubeVideo
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun addToFavorites(video: YouTubeVideo)
    fun getAllFavorites(): Flow<List<YouTubeVideo>>
    suspend fun removeFromFavorites(video: YouTubeVideo)
}