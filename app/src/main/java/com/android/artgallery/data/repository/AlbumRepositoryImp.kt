package com.android.artgallery.data.repository

import com.android.artgallery.domain.model.Album
import com.android.artgallery.data.source.local.AppDatabase
import com.android.artgallery.data.source.remote.RetrofitService
import com.android.artgallery.domain.repository.AlbumRepository
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class AlbumRepositoryImp(private val database: AppDatabase,
                         private val retrofitService: RetrofitService) :
    AlbumRepository {


    override fun getAlbums(): Single<List<Album>> {
        return retrofitService.getAlbums()
    }


}