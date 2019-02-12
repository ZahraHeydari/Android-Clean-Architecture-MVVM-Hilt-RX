package com.android.artgallery.ui.gallery

import android.widget.ImageView
import com.android.artgallery.data.source.Album

/**
 * To make an interaction between [GalleryActivity] & its children
 * */
interface OnGalleryCallback {

    fun navigateToAlbumPage(album: Album)

    fun gotoDetailPageByPhotoId(imageView: ImageView, id: Long)
}