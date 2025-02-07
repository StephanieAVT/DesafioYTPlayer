package com.st.home.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.st.home.domain.favorites.usecase.GetFavoritesUseCase
import com.st.home.domain.favorites.usecase.RemoveFromFavoritesUseCase
import com.st.home.domain.search.model.YouTubeVideo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<YouTubeVideo>>(emptyList())
    val favorites: StateFlow<List<YouTubeVideo>> = _favorites

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            getFavoritesUseCase().collectLatest { videos ->
                _favorites.value = videos
            }
        }
    }

    fun removeFromFavorites(video: YouTubeVideo) {
        viewModelScope.launch {
            removeFromFavoritesUseCase(video)
        }
    }
}
