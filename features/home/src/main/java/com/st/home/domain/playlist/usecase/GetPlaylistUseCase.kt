package com.st.home.domain.playlist.usecase

import com.st.home.domain.playlist.repository.PlaylistRepository
import com.st.home.domain.search.model.YouTubeVideo
import kotlinx.coroutines.flow.Flow

class GetPlaylistUseCase(private val repository: PlaylistRepository) {
    operator fun invoke(): Flow<List<YouTubeVideo>> {
        return repository.getPlaylistVideos()
    }
}
