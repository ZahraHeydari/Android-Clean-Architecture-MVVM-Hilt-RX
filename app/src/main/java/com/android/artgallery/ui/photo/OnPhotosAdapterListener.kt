package com.android.artgallery.ui.photo

import android.widget.ImageView


/**
 * To make an interaction between [PhotosAdapter] & [PhotosFragment]
 * */
interface OnPhotosAdapterListener{

    fun gotoDetailPage(imageView: ImageView,id: Long)

}