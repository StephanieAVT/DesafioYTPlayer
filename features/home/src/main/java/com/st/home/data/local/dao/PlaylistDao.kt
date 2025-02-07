package com.st.home.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.st.home.data.local.entity.PlaylistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(video: PlaylistEntity)

    @Query("SELECT * FROM playlist_videos")
    fun getAllVideos(): Flow<List<PlaylistEntity>>

    @Query("DELETE FROM playlist_videos WHERE videoId = :videoId")
    suspend fun deleteVideo(videoId: String)
}