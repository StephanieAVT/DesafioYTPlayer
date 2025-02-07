package com.st.home.data.playlist.repository


import com.st.home.data.local.dao.PlaylistDao
import com.st.home.data.playlist.mapper.toDomain
import com.st.home.data.playlist.mapper.toEntity
import com.st.home.domain.playlist.repository.PlaylistRepository
import com.st.home.domain.search.model.YouTubeVideo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlaylistRepositoryImpl(private val playlistDao: PlaylistDao) : PlaylistRepository {

    override suspend fun addVideoToPlaylist(video: YouTubeVideo) {
        playlistDao.insertVideo(video.toEntity())
    }

    override fun getPlaylistVideos(): Flow<List<YouTubeVideo>> {
        return playlistDao.getAllVideos().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun removeVideoFromPlaylist(videoId: String) {
        playlistDao.deleteVideo(videoId)
    }
}
