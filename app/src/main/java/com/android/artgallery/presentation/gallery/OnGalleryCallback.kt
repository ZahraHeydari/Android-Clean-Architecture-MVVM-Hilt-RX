package com.android.artgallery.presentation.gallery

import android.widget.ImageView
import com.android.artgallery.domain.model.Album

/**
 * To make an interaction between [GalleryActivity] & its children
 * */
interface OnGalleryCallback {

    fun navigateToAlbumPage(album: Album)

    fun gotoDetailPageByPhotoId(imageView: ImageView, id: Long)
}