package com.android.domain.model

data class Photo(
    var id: Long,
    var title: String,
    val url: String,
    val thumbnailUrl: String?
) {
    fun setName(text: String) {
        title = text
    }
}
