package com.st.home.di

import android.content.Context
import androidx.room.Room
import com.st.home.data.favorites.repository.FavoriteRepositoryImpl
import com.st.home.data.local.AppDatabase
import com.st.home.data.playlist.repository.PlaylistRepositoryImpl
import com.st.home.data.search.api.YouTubeApiService
import com.st.home.data.search.repository.SearchRepositoryImpl
import com.st.home.domain.favorites.repository.FavoriteRepository
import com.st.home.domain.favorites.usecase.AddToFavoritesUseCase
import com.st.home.domain.favorites.usecase.GetFavoritesUseCase
import com.st.home.domain.favorites.usecase.RemoveFromFavoritesUseCase
import com.st.home.domain.playlist.repository.PlaylistRepository
import com.st.home.domain.playlist.usecase.AddToPlaylistUseCase
import com.st.home.domain.playlist.usecase.GetPlaylistUseCase
import com.st.home.domain.playlist.usecase.RemoveFromPlaylistUseCase
import com.st.home.domain.search.repository.SearchRepository
import com.st.home.domain.search.usecase.SearchVideosUseCase
import com.st.home.presentation.favorites.FavoritesViewModel
import com.st.home.presentation.playlist.PlaylistViewModel
import com.st.home.presentation.search.SearchViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val homeModule = module {

    single {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/youtube/v3/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YouTubeApiService::class.java)
    }


    single {
        Room.databaseBuilder(
            get<Context>(),
            AppDatabase::class.java,
            "playlist_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().playlistDao() }
    single { get<AppDatabase>().favoriteDao() }


    factory<SearchRepository> { SearchRepositoryImpl(get()) }
    single<PlaylistRepository> { PlaylistRepositoryImpl(get()) }
    single<FavoriteRepository> { FavoriteRepositoryImpl(get()) }



    viewModel {
        SearchViewModel(
            SearchVideosUseCase(get()), AddToPlaylistUseCase(get()),
            AddToFavoritesUseCase(get())
        )
    }
    viewModel {
        PlaylistViewModel(
            getPlaylistUseCase = GetPlaylistUseCase(get()),
            RemoveFromPlaylistUseCase(get())
        )
    }

    viewModel {
        FavoritesViewModel(
            getFavoritesUseCase = GetFavoritesUseCase(get()),
            removeFromFavoritesUseCase = RemoveFromFavoritesUseCase(get())
        )
    }
}
