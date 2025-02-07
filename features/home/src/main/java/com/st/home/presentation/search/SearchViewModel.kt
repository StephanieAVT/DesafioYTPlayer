package com.st.home.presentation.search

import com.st.home.domain.search.model.YouTubeVideo
import com.st.home.domain.search.usecase.SearchVideosUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.st.home.domain.favorites.usecase.AddToFavoritesUseCase
import com.st.home.domain.playlist.usecase.AddToPlaylistUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchVideosUseCase: SearchVideosUseCase,
    private val addToPlaylistUseCase: AddToPlaylistUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val uiState: StateFlow<SearchUiState> = _uiState

    private val _uiAction = MutableSharedFlow<SearchUiAction>()
    val uiAction: SharedFlow<SearchUiAction> = _uiAction


    fun searchVideos(query: String) {
        _uiState.value = SearchUiState.Loading
        viewModelScope.launch {
            searchVideosUseCase.execute(query).collect { result ->
                result.fold(
                    onSuccess = { response ->
                        _uiState.value = SearchUiState.Success(response.items)
                    },
                    onFailure = { error ->
                        _uiState.value = SearchUiState.Error(error.message ?: "Erro desconhecido")
                    }
                )
            }
        }
    }

    fun addToPlaylist(video: YouTubeVideo) {
        viewModelScope.launch {
            addToPlaylistUseCase(video)
            _uiAction.emit(SearchUiAction.NavigateToPlaylist)
        }
    }

    fun addToFavorites(video: YouTubeVideo) {
        viewModelScope.launch {
            addToFavoritesUseCase(video)
            _uiAction.emit(SearchUiAction.NavigateToFavorites)
        }
    }
}

sealed class SearchUiState {
    object Idle : SearchUiState()
    object Loading : SearchUiState()
    data class Success(val videos: List<YouTubeVideo>) : SearchUiState()
    data class Error(val message: String) : SearchUiState()
}

sealed class SearchUiAction {
    object NavigateToPlaylist : SearchUiAction()
    object NavigateToFavorites : SearchUiAction()

}

