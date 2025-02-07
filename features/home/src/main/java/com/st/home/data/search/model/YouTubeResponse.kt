package com.st.home.data.search.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YouTubeResponse(
    @SerialName("items") val items: List<YouTubeVideoResponse>
)

@Serializable
data class YouTubeVideoResponse(
    @SerialName("id") val id: VideoIdResponse,
    @SerialName("snippet") val snippet: VideoSnippetResponse
)

@Serializable
data class VideoIdResponse(
    @SerialName("videoId") val videoId: String?
)

@Serializable
data class VideoSnippetResponse(
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("thumbnails") val thumbnails: VideoThumbnailsResponse
)

@Serializable
data class VideoThumbnailsResponse(
    @SerialName("medium") val medium: ThumbnailResponse
)

@Serializable
data class ThumbnailResponse(
    @SerialName("url") val url: String
)
