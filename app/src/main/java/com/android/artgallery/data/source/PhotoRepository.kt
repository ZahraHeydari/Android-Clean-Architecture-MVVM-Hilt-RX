package com.android.artgallery.data.source

import com.android.artgallery.data.Photo
import io.reactivex.Single


/**
 * To make an interaction between [PhotoRepositoryImp] & [GetPhotosUseCase]
 * */
interface PhotoRepository {

    fun getPhotos(albumId: Long?): Single<List<Photo>>

    fun getPhotoDetail(photoId: Long?): Single<Photo>
}