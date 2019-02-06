package com.android.artgallery.domain.usecase

import com.android.artgallery.data.Photo
import com.android.artgallery.data.source.PhotoRepository
import com.android.artgallery.domain.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

/**
 * An interactor that calls the actual implementation of [PhotoViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
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