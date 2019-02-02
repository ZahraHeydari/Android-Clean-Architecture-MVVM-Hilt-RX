package com.android.artgallery.data.source

import com.android.artgallery.data.source.local.AppDatabase
import com.android.artgallery.data.source.remote.RetrofitService
import io.reactivex.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class AlbumRepositoryImp(private val database: AppDatabase,
                         private val retrofitService: RetrofitService) : AlbumRepository {


    override fun getAlbums(): Single<List<Album>> {
        return retrofitService.getAlbums()
    }


}