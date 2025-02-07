package com.st.home.data.search.repository

import com.google.common.truth.Truth.assertThat
import com.st.home.data.search.api.YouTubeApiService
import com.st.home.data.search.model.*
import com.st.home.data.search.mapper.toDomain
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SearchRepositoryImplTest {

    private lateinit var repository: SearchRepositoryImpl
    private val apiService: YouTubeApiService = mockk()

    @Before
    fun setUp() {
        repository = SearchRepositoryImpl(apiService)
    }

    @Test
    fun `searchVideos returns success when API call is successful`() = runBlocking {
        val mockResponse = YouTubeResponse(
            items = listOf(
                YouTubeVideoResponse(
                    id = VideoIdResponse(videoId = "testVideoId"),
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

        coEvery { apiService.searchVideos(query = "test") } returns mockResponse

        val result = repository.searchVideos("test")

        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(mockResponse.toDomain())

        coVerify { apiService.searchVideos(query = "test") }
    }

    @Test
    fun `searchVideos returns failure when API call throws exception`() = runBlocking {
        coEvery { apiService.searchVideos(query = "test") } throws Exception("API Error")

        val result = repository.searchVideos("test")

        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()?.message).isEqualTo("API Error")

        coVerify { apiService.searchVideos(query = "test") }
    }
}
