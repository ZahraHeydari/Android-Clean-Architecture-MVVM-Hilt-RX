package com.android.artgallery.presentation.album

import android.arch.lifecycle.MutableLiveData
import com.android.artgallery.domain.model.Album

/**A helper class for the UI controller that is responsible for
 * preparing data for [AlbumViewModel] as the UI
 *
 * @CREATOR ZARA
 * */
class AlbumViewModel(val album: Album) {

    private val TAG = AlbumViewModel::class.java.simpleName
    val albumData = MutableLiveData<Album>()

    init {
        albumData.value = album
    }

}