package com.android.artgallery.ui.photo

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.artgallery.data.Photo

class PhotoViewModel(val photo: Photo) :ViewModel() {

    private val TAG = PhotoViewModel::class.java.simpleName
    val isLoad = MutableLiveData<Boolean>()
    val photoData = MutableLiveData<Photo>()

    init {
        this.photoData.value = photo
    }

    fun set(photo: Photo) = {
        photoData.value = photo
    }

}