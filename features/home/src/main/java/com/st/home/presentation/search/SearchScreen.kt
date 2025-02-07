package com.st.home.presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.st.home.domain.search.model.YouTubeVideo
import com.st.home.navigation.NavRoutes
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = koinViewModel()
) {
    var searchQuery by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()
    val uiAction by viewModel.uiAction.collectAsState(initial = null)

    LaunchedEffect(uiAction) {
        uiAction?.let { action ->
            when (action) {
                is SearchUiAction.NavigateToPlaylist -> {
                    navController.navigate(NavRoutes.PLAYLIST)
                }

                SearchUiAction.NavigateToFavorites -> navController.navigate(NavRoutes.FAVORITES)
            }
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Buscar Vídeos") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Pesquisar") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { viewModel.searchVideos(searchQuery) }) {
                Text("Buscar")
            }
            Spacer(modifier = Modifier.height(16.dp))

            when (uiState) {
                is SearchUiState.Loading -> CircularProgressIndicator()
                is SearchUiState.Success -> {
                    VideoList(
                        videos = (uiState as SearchUiState.Success).videos,
                        onPlayClick = { videoId ->
                            navController.navigate(NavRoutes.PLAYER.replace("{videoId}", videoId))
                        },
                        onAddToPlaylist = { video ->
                            viewModel.addToPlaylist(video)
                        },
                        onFavoriteClick = { video ->
                            viewModel.addToFavorites(video)
                        }
                    )
                }

                is SearchUiState.Error -> Text("Erro: ${(uiState as SearchUiState.Error).message}")
                is SearchUiState.Idle -> {}
            }
        }
    }
}


@Composable
fun VideoList(
    videos: List<YouTubeVideo>,
    onPlayClick: (String) -> Unit,
    onAddToPlaylist: (YouTubeVideo) -> Unit,
    onFavoriteClick: (YouTubeVideo) -> Unit
) {
    LazyColumn {
        items(videos) { video ->
            VideoItem(video, onPlayClick, onAddToPlaylist, onFavoriteClick)
        }
    }
}

@Composable
fun VideoItem(
    video: YouTubeVideo,
    onPlayClick: (String) -> Unit,
    onAddToPlaylist: (YouTubeVideo) -> Unit,
    onFavoriteClick: (YouTubeVideo) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = video.snippet.thumbnails.medium.url,
                contentDescription = "Thumbnail",
                modifier = Modifier.size(100.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = video.snippet.title,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = { onPlayClick(video.id.videoId ?: "") }) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Reproduzir"
                        )
                    }
                    IconButton(onClick = { onAddToPlaylist(video) }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Adicionar à Playlist"
                        )
                    }
                    IconButton(onClick = { onFavoriteClick(video) }) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Favoritar"
                        )
                    }
                }
            }
        }
    }
}

