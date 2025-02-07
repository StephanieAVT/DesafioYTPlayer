package com.st.home.domain.search.repository

import com.st.home.domain.search.model.YouTubeContent

interface SearchRepository {
    suspend fun searchVideos(query: String): Result<YouTubeContent>
}