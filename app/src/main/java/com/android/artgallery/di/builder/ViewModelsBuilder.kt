package com.android.artgallery.di.builder

import android.arch.lifecycle.ViewModel
import com.android.artgallery.di.ViewModelKey
import com.android.artgallery.ui.album.AlbumsViewModel
import com.android.artgallery.ui.detailphoto.PhotoDetailViewModel
import com.android.artgallery.ui.photo.PhotosViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(AlbumsViewModel::class)
    abstract fun bindAlbumsViewModel(albumsViewModel: AlbumsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PhotosViewModel::class)
    abstract fun bindPhotosViewModel(photosViewModel: PhotosViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PhotoDetailViewModel::class)
    abstract fun bindPhotoDetailViewModel(photoDetailViewModel: PhotoDetailViewModel): ViewModel

}