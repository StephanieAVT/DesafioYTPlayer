package com.st.home.domain.search.usecase


import com.st.home.domain.search.model.YouTubeContent
import com.st.home.domain.search.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchVideosUseCase(private val repository: SearchRepository) {
    suspend fun execute(query: String): Flow<Result<YouTubeContent>> = flow {
        emit(repository.searchVideos(query))
    }
}
