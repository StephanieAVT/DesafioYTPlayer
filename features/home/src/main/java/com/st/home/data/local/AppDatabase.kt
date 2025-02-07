package com.st.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.st.home.data.local.dao.PlaylistDao
import com.st.home.data.local.entity.PlaylistEntity
import com.st.home.data.local.dao.FavoriteDao
import com.st.home.data.local.entity.FavoriteEntity

@Database(entities = [PlaylistEntity::class, FavoriteEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playlistDao(): PlaylistDao
    abstract fun favoriteDao(): FavoriteDao

}

