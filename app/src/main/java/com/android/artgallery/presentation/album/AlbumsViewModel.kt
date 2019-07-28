package com.android.artgallery.presentation.album

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.artgallery.domain.model.Album
import com.android.artgallery.domain.usecase.GetAlbumsUseCase
import javax.inject.Inject

/**To store & manage UI-related data in a lifecycle conscious way!
 * this class allows data to survive configuration changes such as screen rotation
 * by interacting with [GetAlbumsUseCase]
 *
 * */
class AlbumsViewModel @Inject constructor(private val getAlbumListUseCase: GetAlbumsUseCase) : ViewModel() {

    private val TAG = AlbumsViewModel::class.java.simpleName
    val albumsReceivedLiveData = MutableLiveData<List<Album>>()
    val isLoad = MutableLiveData<Boolean>()
    val albumData = MutableLiveData<Album>()

    init {
        isLoad.value = false
    }

    val album: Album? get() = albumData.value

    fun set(album: Album) = run { albumData.value = album }

    fun loadAlbums() {
        getAlbumListUseCase.execute(
            onSuccess = {
                isLoad.value = true
                albumsReceivedLiveData.value = it
            },
            onError = {
                it.printStackTrace()
            }
        )
    }
}