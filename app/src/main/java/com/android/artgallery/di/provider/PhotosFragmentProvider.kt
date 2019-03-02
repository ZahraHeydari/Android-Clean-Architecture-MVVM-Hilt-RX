package com.android.artgallery.di.provider

import com.android.artgallery.presentation.photo.PhotosFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class PhotosFragmentProvider{


    @ContributesAndroidInjector
    abstract fun providePhotosFragment(): PhotosFragment
}