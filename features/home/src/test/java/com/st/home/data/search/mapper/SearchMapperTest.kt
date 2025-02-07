package com.st.home.data.search.mapper

import com.google.common.truth.Truth.assertThat
import com.st.home.data.search.model.*
import org.junit.Test

class SearchMapperTest {

    @Test
    fun `YouTubeResponse toDomain should correctly map to YouTubeContent`() {
        val apiResponse = YouTubeResponse(
            items = listOf(
                YouTubeVideoResponse(
                    id = VideoIdResponse("testVideoId"),
                    snippet = VideoSnippetResponse(
                        title = "Test Video",
                        description = "Test Description",
                        thumbnails = VideoThumbnailsResponse(
                            medium = ThumbnailResponse(url = "testUrl")
                        )
                    )
                )
            )
        )

        val domainModel = apiResponse.toDomain()

        assertThat(domainModel.items).hasSize(1)
        assertThat(domainModel.items[0].id.videoId).isEqualTo("testVideoId")
        assertThat(domainModel.items[0].snippet.title).isEqualTo("Test Video")
        assertThat(domainModel.items[0].snippet.thumbnails.medium.url).isEqualTo("testUrl")
    }
}
