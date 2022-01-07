package com.android.artgallery.data.mapper

import com.android.artgallery.data.source.local.entity.PhotoEntity
import com.android.artgallery.domain.model.Photo

fun Photo.toEntity() = PhotoEntity(
    id = id,
    title = title,
    url = url,
    thumbnailUrl = thumbnailUrl
)