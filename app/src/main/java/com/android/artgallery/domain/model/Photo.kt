package com.android.artgallery.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Photo")
data class Photo(
    @PrimaryKey var id: Long,
    var title: String,
    val url: String,
    val thumbnailUrl: String?
) {
    fun setName(text: String) {
        title = text
    }
}