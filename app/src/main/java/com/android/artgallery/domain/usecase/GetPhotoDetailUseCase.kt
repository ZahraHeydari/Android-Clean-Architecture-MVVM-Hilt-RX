package com.android.artgallery.domain.usecase

import com.android.artgallery.data.Photo
import com.android.artgallery.data.source.PhotoRepository
import com.android.artgallery.domain.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetPhotoDetailUseCase @Inject constructor(private val repository: PhotoRepository) : SingleUseCase<Photo>() {

    private var photoId: Long? = null

    fun savePhotoId(id: Long) {
        photoId = id
    }

    override fun buildUseCaseSingle(): Single<Photo> {
        return repository.getPhotoDetail(photoId)
    }

    fun deleteAsFavorite(photo: Photo) {
        repository.deletePhoto(photo)
    }

    fun addAsFavorite(photo: Photo) {
        repository.addPhoto(photo)
    }

    fun isFavorite(photoId: Long): Boolean {
        return repository.isFavorite(photoId)
    }
}