package com.st.home.presentation.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.st.home.domain.playlist.usecase.GetPlaylistUseCase
import com.st.home.domain.playlist.usecase.RemoveFromPlaylistUseCase
import com.st.home.domain.search.model.YouTubeVideo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlaylistViewModel(
    private val getPlaylistUseCase: GetPlaylistUseCase,
    private val removeFromPlaylistUseCase: RemoveFromPlaylistUseCase
) : ViewModel() {

    private val _playlistVideos = MutableStateFlow<List<YouTubeVideo>>(emptyList())
    val playlistVideos: StateFlow<List<YouTubeVideo>> = _playlistVideos

    init {
        viewModelScope.launch {
            getPlaylistUseCase().collect { videos ->
                _playlistVideos.value = videos
            }
        }
    }


    fun removeFromPlaylist(videoId: String) {
        viewModelScope.launch {
            removeFromPlaylistUseCase(videoId)
        }
    }
}