package com.android.artgallery

import com.android.domain.model.Photo

object TestUtil {

    fun createPhoto(id: Long) = com.android.domain.model.Photo(
        id = id,
        title = "",
        url = "",
        thumbnailUrl = ""
    )

    fun makePhotoList(size: Int): MutableList<com.android.domain.model.Photo> {
        val list = ArrayList<com.android.domain.model.Photo>(size)
        list.forEach {
            it.title = "Photo ${list.indexOf(it)}"
            it.id = (list.indexOf(it) + 1).toLong()
            list.add(it)
        }
        return list
    }
}
