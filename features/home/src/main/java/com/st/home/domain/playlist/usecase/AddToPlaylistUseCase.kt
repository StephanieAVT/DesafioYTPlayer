package com.st.home.domain.playlist.usecase

import com.st.home.domain.playlist.repository.PlaylistRepository
import com.st.home.domain.search.model.YouTubeVideo

class AddToPlaylistUseCase(private val repository: PlaylistRepository) {
    suspend operator fun invoke(video: YouTubeVideo) {
        repository.addVideoToPlaylist(video)
    }
}
