package com.android.artgallery.presentation.album

import com.android.artgallery.data.source.Album

/**
 * To make an interaction between [AlbumsAdapter] & [AlbumsFragment]
 * */
interface OnAlbumsAdapterListener {

    fun showPhotos(album: Album)
}