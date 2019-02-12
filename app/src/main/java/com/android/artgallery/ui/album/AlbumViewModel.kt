package com.android.artgallery.ui.album

import android.arch.lifecycle.MutableLiveData
import com.android.artgallery.data.source.Album

/**A helper class for the UI controller that is responsible for
 * preparing data for [AlbumViewModel] as the UI
 *
 * @CREATOR ZARA
 * */
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