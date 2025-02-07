package com.st.home.domain.playlist.usecase

import com.st.home.domain.playlist.repository.PlaylistRepository

class RemoveFromPlaylistUseCase(private val repository: PlaylistRepository) {
    suspend operator fun invoke(videoId: String) {
        repository.removeVideoFromPlaylist(videoId)
    }
}