package com.android.artgallery.domain.usecase

import com.android.artgallery.data.source.Album
import com.android.artgallery.data.source.AlbumRepository
import com.android.artgallery.domain.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject



class GetAlbumsUseCase @Inject constructor(private val repository: AlbumRepository): SingleUseCase<List<Album>>() {


    override fun buildUseCaseSingle(): Single<List<Album>> {
        return repository.getAlbums()
    }
}