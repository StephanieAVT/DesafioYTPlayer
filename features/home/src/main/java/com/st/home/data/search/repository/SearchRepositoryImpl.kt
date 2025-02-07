package com.st.home.data.search.repository

import com.st.home.data.search.api.YouTubeApiService
import com.st.home.data.search.mapper.toDomain
import com.st.home.domain.search.model.YouTubeContent
import com.st.home.domain.search.repository.SearchRepository


class SearchRepositoryImpl(private val apiService: YouTubeApiService): SearchRepository {
   override suspend fun searchVideos(query: String): Result<YouTubeContent> {
        return try {
            val response = apiService.searchVideos(query = query).toDomain()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}