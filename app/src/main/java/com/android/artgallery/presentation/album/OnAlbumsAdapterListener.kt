package com.android.artgallery.presentation.album

import com.android.artgallery.domain.model.Album

/**
 * To make an interaction between [AlbumsAdapter] & [AlbumsFragment]
 * */
interface OnAlbumsAdapterListener {

    fun showPhotos(album: Album)
}