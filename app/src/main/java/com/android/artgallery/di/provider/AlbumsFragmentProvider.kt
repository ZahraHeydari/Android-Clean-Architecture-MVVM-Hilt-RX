package com.android.artgallery.di.provider

import com.android.artgallery.presentation.album.AlbumsFragment
import com.android.artgallery.presentation.photo.PhotosFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AlbumsFragmentProvider {

    @ContributesAndroidInjector
    abstract fun provideAlbumsFragment(): AlbumsFragment

}