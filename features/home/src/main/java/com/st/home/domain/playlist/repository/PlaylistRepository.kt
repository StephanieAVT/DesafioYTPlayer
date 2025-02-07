package com.st.home.domain.playlist.repository

import com.st.home.domain.search.model.YouTubeVideo
import kotlinx.coroutines.flow.Flow

interface PlaylistRepository {
    suspend fun addVideoToPlaylist(video: YouTubeVideo)
    fun getPlaylistVideos(): Flow<List<YouTubeVideo>>
    suspend fun removeVideoFromPlaylist(videoId: String)
}