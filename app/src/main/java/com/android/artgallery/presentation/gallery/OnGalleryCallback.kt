package com.android.artgallery.presentation.gallery

import com.android.artgallery.data.source.Album

/**
 * To make an interaction between [GalleryActivity] & its children*/
interface OnGalleryCallback {

    fun navigateToAlbumPage(album: Album)

    fun gotoDetailPageByPhotoId(id: Long)
}