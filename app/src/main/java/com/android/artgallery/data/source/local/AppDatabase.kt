package com.android.artgallery.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.android.artgallery.data.Photo
import com.android.artgallery.data.source.local.dao.PhotoDao

@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val photoDao: PhotoDao

    companion object {
        public val DB_NAME = "ArtGalleryDatabase.db"
    }
}
