package com.st.home.presentation.playlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.st.home.domain.search.model.YouTubeVideo
import com.st.home.navigation.NavRoutes
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistScreen(
    navController: NavController,
    viewModel: PlaylistViewModel = koinViewModel()
) {
    val playlistVideos by viewModel.playlistVideos.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Minha Playlist") })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(playlistVideos) { video ->
                PlaylistItem(
                    video,
                    onPlayClick = { videoId ->
                        navController.navigate(NavRoutes.PLAYER.replace("{videoId}", videoId))
                    },
                    onRemoveClick = { viewModel.removeFromPlaylist(video.id.videoId ?: "") })
            }
        }
    }
}

@Composable
fun PlaylistItem(video: YouTubeVideo, onRemoveClick: () -> Unit, onPlayClick: (String) -> Unit) {
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
            Image(
                painter = rememberAsyncImagePainter(video.snippet.thumbnails.medium.url),
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
                    IconButton(onClick = onRemoveClick) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Remover da Playlist"
                        )
                    }

                }
            }
        }
    }
}

