package com.android.artgallery.presentation.photo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.artgallery.domain.model.Photo
import com.android.artgallery.domain.usecase.GetPhotosUseCase
import javax.inject.Inject

/**A helper class for the UI controller that is responsible for
 * preparing data for the UI [PhotosFragment]
 *
 * @author ZARA
 * */
class PhotosViewModel @ViewModelInject constructor(private val getPhotosUseCase: GetPhotosUseCase) : ViewModel() {

    private val TAG = PhotosViewModel::class.java.simpleName
    val photoListReceivedLiveData = MutableLiveData<List<Photo>>()
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