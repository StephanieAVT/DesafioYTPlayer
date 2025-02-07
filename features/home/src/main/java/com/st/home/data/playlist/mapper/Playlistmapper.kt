package com.st.home.data.playlist.mapper

import com.st.home.data.local.entity.PlaylistEntity
import com.st.home.data.search.model.ThumbnailResponse
import com.st.home.data.search.model.VideoIdResponse
import com.st.home.data.search.model.VideoSnippetResponse
import com.st.home.data.search.model.VideoThumbnailsResponse
import com.st.home.data.search.model.YouTubeVideoResponse
import com.st.home.domain.search.model.Thumbnail
import com.st.home.domain.search.model.VideoId
import com.st.home.domain.search.model.VideoSnippet
import com.st.home.domain.search.model.VideoThumbnails
import com.st.home.domain.search.model.YouTubeVideo

fun PlaylistEntity.toDomain(): YouTubeVideo {
    return YouTubeVideo(
        id = VideoId(videoId),
        snippet = VideoSnippet(
            title = title,
            description = description,
            thumbnails = VideoThumbnails(
                medium = Thumbnail(thumbnailUrl)
            )
        )
    )
}
fun YouTubeVideo.toEntity() = PlaylistEntity(
    videoId = id.videoId ?: "",
    title = snippet.title,
    description = snippet.description,
    thumbnailUrl = snippet.thumbnails.medium.url
)
