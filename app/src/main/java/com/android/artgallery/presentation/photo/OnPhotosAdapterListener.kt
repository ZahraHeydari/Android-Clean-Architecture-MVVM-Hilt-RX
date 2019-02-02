package com.android.artgallery.presentation.photo


/**
 * To make an interaction between [PhotosAdapter] & [PhotosFragment]
 * */
interface OnPhotosAdapterListener{

    fun gotoDetailPage(id: Long)

}