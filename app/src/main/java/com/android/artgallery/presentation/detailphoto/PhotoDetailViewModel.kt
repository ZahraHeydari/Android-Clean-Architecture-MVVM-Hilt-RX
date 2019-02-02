package com.android.artgallery.presentation.detailphoto

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.artgallery.data.Photo
import com.android.artgallery.domain.usecase.GetPhotoDetailUseCase
import javax.inject.Inject

class PhotoDetailViewModel@Inject constructor(private val getPhotoDetailUseCase: GetPhotoDetailUseCase) : ViewModel() {

    private val TAG = PhotoDetailViewModel::class.java.simpleName
    val photoData = MutableLiveData<Photo>()
    val isLoad = MutableLiveData<Boolean>()

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
}