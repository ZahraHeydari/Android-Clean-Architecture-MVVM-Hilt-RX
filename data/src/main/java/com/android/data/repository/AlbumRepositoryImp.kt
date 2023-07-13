package com.android.data.repository

import com.android.data.source.remote.RetrofitService
import com.android.domain.model.Album
import com.android.domain.repository.AlbumRepository
import io.reactivex.Single

/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class AlbumRepositoryImp(
    private val retrofitService: RetrofitService
) : AlbumRepository {

    override fun getAlbums(): Single<List<Album>> {
        return retrofitService.getAlbums()
    }
}
