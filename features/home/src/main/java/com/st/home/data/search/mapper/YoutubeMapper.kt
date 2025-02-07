package com.st.home.data.search.mapper

import com.st.home.data.search.model.*
import com.st.home.data.search.model.ThumbnailResponse
import com.st.home.data.search.model.VideoIdResponse
import com.st.home.data.search.model.VideoSnippetResponse
import com.st.home.data.search.model.VideoThumbnailsResponse
import com.st.home.data.search.model.YouTubeVideoResponse
import com.st.home.domain.search.model.Thumbnail
import com.st.home.domain.search.model.VideoId
import com.st.home.domain.search.model.VideoSnippet
import com.st.home.domain.search.model.VideoThumbnails
import com.st.home.domain.search.model.YouTubeContent
import com.st.home.domain.search.model.YouTubeVideo

fun YouTubeResponse.toDomain(): YouTubeContent {
    return YouTubeContent(
        items = this.items.map { it.toDomain() }
    )
}

fun YouTubeVideoResponse.toDomain(): YouTubeVideo {
    return YouTubeVideo(
        id = this.id.toDomain(),
        snippet = this.snippet.toDomain()
    )
}

fun VideoIdResponse.toDomain(): VideoId {
    return VideoId(
        videoId = this.videoId
    )
}

fun VideoSnippetResponse.toDomain(): VideoSnippet {
    return VideoSnippet(
        title = this.title,
        description = this.description,
        thumbnails = this.thumbnails.toDomain()
    )
}

fun VideoThumbnailsResponse.toDomain(): VideoThumbnails {
    return VideoThumbnails(
        medium = this.medium.toDomain()
    )
}

fun ThumbnailResponse.toDomain(): Thumbnail {
    return Thumbnail(
        url = this.url
    )
}
