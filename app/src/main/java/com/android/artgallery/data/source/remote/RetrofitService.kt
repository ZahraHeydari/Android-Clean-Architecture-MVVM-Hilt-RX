package com.android.artgallery.data.source.remote

import com.android.artgallery.domain.model.Photo
import com.android.artgallery.domain.model.Album
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("albums/")
    fun getAlbums(): Single<List<Album>>

    @GET("albums/{id}/photos")
    fun getPhotos(@Path("id") id: Long): Single<List<Photo>>

    @GET("photos/{id}")
    fun getPhotoDetail(@Path("id") id: Long):Single<Photo>
}