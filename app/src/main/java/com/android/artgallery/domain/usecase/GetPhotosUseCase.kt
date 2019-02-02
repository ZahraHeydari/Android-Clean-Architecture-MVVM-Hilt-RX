package com.android.artgallery.domain.usecase

import com.android.artgallery.data.Photo
import com.android.artgallery.data.source.PhotoRepository
import com.android.artgallery.domain.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject


class GetPhotosUseCase @Inject constructor(private val repository: PhotoRepository) : SingleUseCase<List<Photo>>() {

    private var albumId: Long? = null

    fun saveAlbumId(id: Long) {
        albumId = id
    }

    override fun buildUseCaseSingle(): Single<List<Photo>> {
        return repository.getPhotos(albumId)
    }
}