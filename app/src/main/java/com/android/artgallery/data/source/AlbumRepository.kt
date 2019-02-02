package com.android.artgallery.data.source

import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface AlbumRepository {

    fun getAlbums(): Single<List<Album>>
}