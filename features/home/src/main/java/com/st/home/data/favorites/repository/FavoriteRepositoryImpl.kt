package com.st.home.data.favorites.repository

import com.st.home.data.local.dao.FavoriteDao
import com.st.home.data.local.entity.FavoriteEntity
import com.st.home.domain.favorites.repository.FavoriteRepository
import com.st.home.domain.search.model.Thumbnail
import com.st.home.domain.search.model.VideoThumbnails
import com.st.home.domain.search.model.YouTubeVideo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteRepositoryImpl(private val dao: FavoriteDao) : FavoriteRepository {

    override suspend fun addToFavorites(video: YouTubeVideo) {
        dao.addToFavorites(
            FavoriteEntity(
                videoId = video.id.videoId ?: "",
                title = video.snippet.title,
                description = video.snippet.description,
                thumbnailUrl = video.snippet.thumbnails.medium.url
            )
        )
    }

    override fun getAllFavorites(): Flow<List<YouTubeVideo>> {
        return dao.getAllFavorites().map { favorites ->
            favorites.map { favorite ->
                YouTubeVideo(
                    id = com.st.home.domain.search.model.VideoId(favorite.videoId),
                    snippet = com.st.home.domain.search.model.VideoSnippet(
                        title = favorite.title,
                        description = favorite.description,
                        thumbnails = VideoThumbnails(
                            medium = Thumbnail(
                                url = favorite.thumbnailUrl
                            )
                        )
                    )
                )
            }
        }
    }

    override suspend fun removeFromFavorites(video: YouTubeVideo) {
        dao.removeFromFavorites(
            FavoriteEntity(
                videoId = video.id.videoId ?: "",
                title = video.snippet.title,
                description = video.snippet.description,
                thumbnailUrl = video.snippet.thumbnails.medium.url
            )
        )
    }
}
