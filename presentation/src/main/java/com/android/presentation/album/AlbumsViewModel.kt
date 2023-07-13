package com.android.presentation.album

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.domain.model.Album
import com.android.domain.usecase.GetAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**To store & manage UI-related data in a lifecycle conscious way!
 * this class allows data to survive configuration changes such as screen rotation
 * by interacting with [GetAlbumsUseCase]
 *
 * */
@HiltViewModel
class AlbumsViewModel @Inject constructor(private val getAlbumListUseCase: com.android.domain.usecase.GetAlbumsUseCase) :
    ViewModel() {

    val albumsReceivedLiveData = MutableLiveData<List<com.android.domain.model.Album>>()
    val isLoad = MutableLiveData<Boolean>()
    val albumData = MutableLiveData<com.android.domain.model.Album>()

    init {
        isLoad.value = false
    }

    val album: com.android.domain.model.Album? get() = albumData.value

    fun set(album: com.android.domain.model.Album) = run { albumData.value = album }

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
