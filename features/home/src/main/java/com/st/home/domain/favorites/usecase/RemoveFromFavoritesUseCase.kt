package com.st.home.domain.favorites.usecase

import com.st.home.domain.favorites.repository.FavoriteRepository
import com.st.home.domain.search.model.YouTubeVideo

class RemoveFromFavoritesUseCase(private val repository: FavoriteRepository) {
    suspend operator fun invoke(video: YouTubeVideo) = repository.removeFromFavorites(video)
}