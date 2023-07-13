package com.android.presentation.photo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.domain.model.Photo
import com.android.domain.usecase.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**A helper class for the UI controller that is responsible for
 * preparing data for the UI [PhotosFragment]
 *
 * @author ZARA
 * */
@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val getPhotosUseCase: com.android.domain.usecase.GetPhotosUseCase
) : ViewModel() {

    val photoListReceivedLiveData = MutableLiveData<List<com.android.domain.model.Photo>>()
    val isLoad = MutableLiveData<Boolean>()

    init {
        isLoad.value = false
    }

    fun loadPhotos(id: Long?) {
        if (id == null) return
        getPhotosUseCase.saveAlbumId(id)
        getPhotosUseCase.execute(
            onSuccess = {
                isLoad.value = true
                photoListReceivedLiveData.value = it
            },
            onError = {
                it.printStackTrace()
            }
        )
    }
}
