package com.android.data

import com.android.data.source.local.entity.PhotoEntity

object TestUtil {

    fun createPhoto(id: Long) = PhotoEntity(
        id = id,
        title = "",
        url = "",
        thumbnailUrl = ""
    )

    fun makePhotoList(size: Int): MutableList<PhotoEntity> {
        val list = ArrayList<PhotoEntity>(size)
        list.forEach {
            it.title = "Photo ${list.indexOf(it)}"
            it.id = (list.indexOf(it) + 1).toLong()
            list.add(it)
        }
        return list
    }
}
