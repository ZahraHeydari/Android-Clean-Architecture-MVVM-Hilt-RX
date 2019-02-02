package com.android.artgallery.presentation.album

import android.arch.lifecycle.MutableLiveData
import com.android.artgallery.data.source.Album


class AlbumViewModel {

    private val TAG = AlbumViewModel::class.java.simpleName
    val isLoad = MutableLiveData<Boolean>()
    val albumData = MutableLiveData<Album>()

    constructor(album: Album) {
        this.albumData.value = album
    }

    val album: Album? get() = albumData.value

    fun set(album: Album) = {
        albumData.value = album
    }

}