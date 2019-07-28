package com.android.artgallery.presentation.photo

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.artgallery.domain.model.Photo

class PhotoViewModel(val photo: Photo) :ViewModel() {

    private val TAG = PhotoViewModel::class.java.name
    val photoData = MutableLiveData<Photo>()

    init {
        photoData.value = photo
    }

}