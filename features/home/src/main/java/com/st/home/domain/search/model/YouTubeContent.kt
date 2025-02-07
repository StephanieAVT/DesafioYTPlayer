package com.st.home.domain.search.model


data class YouTubeContent(
    val items: List<YouTubeVideo>
)

data class YouTubeVideo(
    val id: VideoId,
    val snippet: VideoSnippet
)


data class VideoId(
    val videoId: String?
)

data class VideoSnippet(
    val title: String,
    val description: String,
    val thumbnails: VideoThumbnails
)


data class VideoThumbnails(
    val medium: Thumbnail
)


data class Thumbnail(
    val url: String
)
