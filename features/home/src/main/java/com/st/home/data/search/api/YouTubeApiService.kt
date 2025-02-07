package com.st.home.data.search.api

import com.st.home.data.search.model.YouTubeResponse
import retrofit2.http.GET
import retrofit2.http.Query
import com.st.home.BuildConfig

interface YouTubeApiService {
    @GET("search")
    suspend fun searchVideos(
        @Query("part") part: String = "snippet",
        @Query("maxResults") maxResults: Int = 10,
        @Query("q") query: String,
        @Query("key") apiKey: String = BuildConfig.YOUTUBE_API_KEY
    ): YouTubeResponse
}

