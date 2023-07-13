package com.android.presentation.detailphoto

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.domain.model.Photo
import com.android.domain.usecase.GetPhotoDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * A helper class for the UI controller that is responsible for
 * preparing data for [PhotoDetailFragment]
 *
 * @author ZARA
 * */
@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val getPhotoDetailUseCase: com.android.domain.usecase.GetPhotoDetailUseCase
) : ViewModel() {

    val photoData = MutableLiveData<com.android.domain.model.Photo>()
    val isLoad = MutableLiveData<Boolean>()
    val isFavorite = MutableLiveData<Boolean>()

    init {
        isLoad.value = false
    }

    fun getDetail(id: Long?) {
        if (id == null) return
        getPhotoDetailUseCase.savePhotoId(id)
        getPhotoDetailUseCase.execute(
            onSuccess = {
                isLoad.value = true
                photoData.value = it
            },
            onError = {
                it.printStackTrace()
            }
        )
    }

    fun updateFavoriteStatus() {
        photoData.value?.let { photo ->
            if (isFavorite.value == true) {
                getPhotoDetailUseCase.deleteAsFavorite(photo)
            } else {
                getPhotoDetailUseCase.addAsFavorite(photo)
            }
            isFavorite.value?.let {
                isFavorite.value = !it
            }
        }
    }

    fun checkFavoriteStatus(photoId: Long) {
        isFavorite.value = getPhotoDetailUseCase.isFavorite(photoId)
    }
}
