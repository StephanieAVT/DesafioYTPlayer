package com.st.home.data.search.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class YouTubeApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: YouTubeApiService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YouTubeApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `searchVideos returns expected response`() = runBlocking {
        val mockResponse = MockResponse()
            .setBody(
                """
                {
                    "items": [
                        {
                            "id": { "videoId": "testVideoId" },
                            "snippet": {
                                "title": "Test Video",
                                "description": "Test Description",
                                "thumbnails": { "medium": { "url": "testUrl" } }
                            }
                        }
                    ]
                }
                """
            )
            .setResponseCode(200)

        mockWebServer.enqueue(mockResponse)

        val result = apiService.searchVideos(query = "test")

        assertThat(result.items).isNotEmpty()
        assertThat(result.items.first().id.videoId).isEqualTo("testVideoId")
        assertThat(result.items.first().snippet.title).isEqualTo("Test Video")
    }
}
