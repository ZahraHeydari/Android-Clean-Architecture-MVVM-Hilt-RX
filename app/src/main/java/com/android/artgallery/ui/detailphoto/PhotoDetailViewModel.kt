package com.android.artgallery.ui.detailphoto

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.artgallery.data.Photo
import com.android.artgallery.domain.usecase.GetPhotoDetailUseCase
import javax.inject.Inject

/**
 * A helper class for the UI controller that is responsible for
 * preparing data for [PhotoDetailActivity]
 *
 * @author ZARA
 * */
class PhotoDetailViewModel @Inject constructor(private val getPhotoDetailUseCase: GetPhotoDetailUseCase) : ViewModel() {

    private val TAG = PhotoDetailViewModel::class.java.simpleName
    val photoData = MutableLiveData<Photo>()
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
        if (photoData.value == null) return
        if (isFavorite.value == true) {
            isFavorite.value = false
            getPhotoDetailUseCase.deleteAsFavorite(photoData.value!!)
        } else {
            isFavorite.value = true
            getPhotoDetailUseCase.addAsFavorite(photoData.value!!)
        }
    }

    fun checkFavoriteStatus(photoId: Long) {
        isFavorite.value = getPhotoDetailUseCase.isFavorite(photoId)
    }
}