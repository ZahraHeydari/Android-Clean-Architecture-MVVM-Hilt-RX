package com.android.artgallery.di.provider

import com.android.artgallery.ui.album.AlbumsFragment
import com.android.artgallery.ui.photo.PhotosFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class GalleryActivityProviders {

    @ContributesAndroidInjector
    abstract fun provideAlbumsFragment(): AlbumsFragment

    @ContributesAndroidInjector
    abstract fun providePhotosFragment(): PhotosFragment

}