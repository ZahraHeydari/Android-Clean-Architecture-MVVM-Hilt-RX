package com.android.domain.repository

import com.android.domain.model.Album
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface AlbumRepository {
    fun getAlbums(): Single<List<Album>>
}
