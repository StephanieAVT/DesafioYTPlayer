package com.st.home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playlist_videos")
data class PlaylistEntity(
    @PrimaryKey val videoId: String,
    val title: String,
    val description: String,
    val thumbnailUrl: String
)
