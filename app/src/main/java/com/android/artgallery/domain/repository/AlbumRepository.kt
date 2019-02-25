package com.android.artgallery.domain.repository

import com.android.artgallery.data.source.Album
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface AlbumRepository {

    fun getAlbums(): Single<List<Album>>
}