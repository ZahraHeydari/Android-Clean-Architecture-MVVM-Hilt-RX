package com.android.artgallery.di.module

import com.android.artgallery.di.provider.AlbumsFragmentProvider
import com.android.artgallery.di.provider.PhotosFragmentProvider
import com.android.artgallery.presentation.detailphoto.PhotoDetailActivity
import com.android.artgallery.presentation.gallery.GalleryActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
interface ActivityModule {

    @ContributesAndroidInjector(
        modules = [
            AlbumsFragmentProvider::class,
            PhotosFragmentProvider::class
        ])
    fun galleryActivityInjector(): GalleryActivity

    @ContributesAndroidInjector
    fun photoDetailActivityInjector(): PhotoDetailActivity

}