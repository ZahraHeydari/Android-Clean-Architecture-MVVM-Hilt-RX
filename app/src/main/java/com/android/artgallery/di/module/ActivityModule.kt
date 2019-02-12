package com.android.artgallery.di.module

import com.android.artgallery.di.provider.GalleryActivityProviders
import com.android.artgallery.ui.detailphoto.PhotoDetailActivity
import com.android.artgallery.ui.gallery.GalleryActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
interface ActivityModule {

    @ContributesAndroidInjector(modules = [GalleryActivityProviders::class])
    fun galleryActivityInjector(): GalleryActivity

    @ContributesAndroidInjector
    fun photoDetailActivityInjector(): PhotoDetailActivity

}