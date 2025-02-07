package com.st.home.domain.favorites.usecase

import com.st.home.domain.favorites.repository.FavoriteRepository

class GetFavoritesUseCase(private val repository: FavoriteRepository) {
    operator fun invoke() = repository.getAllFavorites()
}
