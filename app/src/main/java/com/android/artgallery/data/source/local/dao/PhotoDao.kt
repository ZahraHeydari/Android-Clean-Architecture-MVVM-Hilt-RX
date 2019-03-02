package com.android.artgallery.data.source.local.dao


import android.arch.persistence.room.*
import com.android.artgallery.domain.model.Photo

/**
 * it provides access to [Photo] underlying database
 * */
@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photo: Photo): Long

    @Query("SELECT * FROM Photo")
    fun loadAll(): MutableList<Photo>

    @Delete
    fun delete(photo: Photo)

    @Query("DELETE FROM Photo")
    fun deleteAll()

    @Query("SELECT * FROM Photo where id = :photoId")
    fun loadOneByPhotoId(photoId: Long): Photo?

    @Query("SELECT * FROM Photo where title = :photoTitle")
    fun loadOneByPhotoTitle(photoTitle: String): Photo?

    @Update
    fun update(photo: Photo)

}