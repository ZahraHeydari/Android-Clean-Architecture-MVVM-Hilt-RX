package com.android.artgallery.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.android.artgallery.domain.model.Photo
import com.android.artgallery.data.source.local.dao.PhotoDao

/**
 * To manage data items that can be accessed, updated
 * & maintain relationships between them
 *
 * @Created by ZARA
 */
@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val photoDao: PhotoDao

    companion object {
        val DB_NAME = "ArtGalleryDatabase.db"
    }
}
